package cn.com.goodlan.its.core.service.event.approval;

import cn.com.goodlan.its.core.dao.primary.event.EventRepository;
import cn.com.goodlan.its.core.dao.primary.system.region.RegionRepository;
import cn.com.goodlan.its.core.mapstruct.EventMapper;
import cn.com.goodlan.its.core.pojo.Params;
import cn.com.goodlan.its.core.pojo.dto.EventDTO;
import cn.com.goodlan.its.core.pojo.entity.primary.Region;
import cn.com.goodlan.its.core.pojo.entity.primary.Score;
import cn.com.goodlan.its.core.pojo.entity.primary.Vehicle;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import cn.com.goodlan.its.core.pojo.query.EventQuery;
import cn.com.goodlan.its.core.pojo.vo.EventVO;
import cn.com.goodlan.its.core.service.event.CountService;
import cn.com.goodlan.its.core.service.system.score.ScoreService;
import cn.com.goodlan.its.core.service.system.vehicle.VehicleService;
import cn.com.goodlan.its.core.util.DateUtils;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    private RegionRepository regionRepository;

    private VehicleService vehicleService;

    private CountService countService;

    private ScoreService scoreService;

    private EventMapper eventMapper;

    @Override
    public Page<EventVO> search(EventQuery eventQuery, Pageable pageable) {
        Specification<Event> specification = querySpecification(eventQuery);
        Page<Event> page = eventRepository.findAll(specification, pageable);
        List<EventVO> list = eventMapper.convertList(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public void create(EventDTO eventDTO) {
        Vehicle vehicle = new Vehicle(eventDTO.getVehicleNumber(), eventDTO.getDriverName(), eventDTO.getDriverPhone(), eventDTO.getCollegeName(), eventDTO.getStudentNum());

        Long count = countService.queryCountThisYear(eventDTO.getVehicleNumber(), eventDTO.getViolationTypeName());

        Region region = regionRepository.getById(eventDTO.getRegionId());

        Score score = scoreService.getScore(region.getScoreList(), Integer.valueOf(eventDTO.getSpeed()));


        Event event = new Event(Event.Source.MANUAL);
        event.updateVehicle(vehicle);
        event.updateCount(count);
        event.updateHappenTime(LocalDateTimeUtil.parse(eventDTO.getHappenTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        eventRepository.save(event);
    }

    @Override
    public List<EventVO> export(EventQuery eventQuery) {
        Specification<Event> specification = querySpecification(eventQuery);
        List<Event> list = eventRepository.findAll(specification);
        return eventMapper.convertList(list);
    }

    @Override
    public void remove(String ids) {
        String[] eventIds = Convert.toStrArray(ids);
        for (String id : eventIds) {
            Event event = eventRepository.getById(id);
            event.remove();

            String violationName = event.getViolationName();
            if (violationName.startsWith("超速")) {
                violationName = "超速";
            } else {
                violationName = "违章停车";
            }


            LocalDateTime firstDay = DateUtils.getFirstDayOfYear(event.getTime());

            LocalDateTime lastDay = DateUtils.getLastDayOfYear(event.getTime());

            // 查询需要刷新的历史数据
            List<Event> list = eventRepository.findByTimeGreaterThanEqualAndTimeLessThanEqualAndViolationName(firstDay, lastDay, violationName + "%", event.getLicensePlateNumber(), Event.Deleted.NORMAL);

            refreshNumAndScore(list);

        }


    }


    /**
     * 刷新历史数据
     */
    private void refreshNumAndScore(List<Event> list) {
        long num = 1;
        for (Event event : list) {
            event.updateCount(num);
            num++;
        }
        for (Event event : list) {
            if (event.getNum() == 1) {
                if (event.isSpeed1() || event.isSpeed2() || event.isStop()) {
                    event.updateScore(0);
                    break;
                }
                if (event.isSpeed3()) {
                    event.updateScore(event.getScoreId());
                }
                log.error("未知的违规类型:{}", event.getViolationName());
            }
        }
    }

    private Specification<Event> querySpecification(EventQuery eventQuery) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(eventQuery.getCollegeName())) {
                list.add(criteriaBuilder.like(root.get("collegeName").as(String.class), eventQuery.getCollegeName() + "%"));
            }
            if (StringUtils.isNotEmpty(eventQuery.getViolationTypeId())) {
                CriteriaBuilder.In<String> violationIdIn = criteriaBuilder.in(root.get("violationId").as(String.class));
                String[] violationIds = StringUtils.split(eventQuery.getViolationTypeId(), ',');
                for (String violationId : violationIds) {
                    violationIdIn.value(violationId);
                }
                list.add(violationIdIn);
            }
            if (StringUtils.isNotEmpty(eventQuery.getDriverName())) {
                list.add(criteriaBuilder.like(root.get("driverName").as(String.class), eventQuery.getDriverName() + "%"));
            }
            if (StringUtils.isNotEmpty(eventQuery.getVehicleNumber())) {
                list.add(criteriaBuilder.like(root.get("licensePlateNumber").as(String.class), "%" + eventQuery.getVehicleNumber() + "%"));
            }
            if (StringUtils.isNotEmpty(eventQuery.getStartTime())) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("time").as(String.class), eventQuery.getStartTime()));
            }
            if (StringUtils.isNotEmpty(eventQuery.getEndTime())) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("time").as(String.class), eventQuery.getEndTime()));
            }
            list.add(criteriaBuilder.equal(root.get("deleted").as(Event.Deleted.class), Event.Deleted.NORMAL));
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        };
    }

    @Override
    public EventVO getById(String id) {
        Event event = eventRepository.getById(id);
        return eventMapper.convert(event);
    }


    /**
     * 根据工号查询违章集合
     *
     * @param studstaffno 工号
     * @param pageable    分页
     * @return JSONObject
     */
    @Override
    public Map<String, Object> findByUserId(String studstaffno, Pageable pageable) {
        if (StringUtils.isBlank(studstaffno) || Objects.equals(studstaffno, "undefined")) {
            Map<String, Object> map = new HashMap<>(4);
            map.put("pageSize", pageable.getPageSize());
            map.put("pageIndex", pageable.getPageNumber());
            map.put("total", 0);
            map.put("eventList", new ArrayList<>());
            return map;
        }
        //根据工号查询车辆集合
        List<Vehicle> vehicles = vehicleService.findByStudstaffno(studstaffno);
        List<String> collect = vehicles.stream().map(Vehicle::getLicensePlateNumber).collect(Collectors.toList());
        //根据车辆车牌集合查询违章事件
        Page<Event> eventList = eventRepository.findByLicensePlateNumberInAndDeletedOrderByTimeDesc(collect, pageable, Event.Deleted.NORMAL);
        return getStringObjectMap(pageable, eventList);
    }

    @Override
    public Map<String, Object> allEvent(Params params) {
        //查询全部违章
        Pageable pageable = params.getPage();
        Page<Event> eventList = eventRepository.findAllByDeletedOrderByTimeDesc(Event.Deleted.NORMAL, pageable);
        return getStringObjectMap(pageable, eventList);
    }

    private Map<String, Object> getStringObjectMap(Pageable pageable, Page<Event> eventList) {
        List<EventVO> eventVOS = eventList.stream().map(eventMapper::convert).collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>(4);
        map.put("pageSize", pageable.getPageSize());
        map.put("pageIndex", pageable.getPageNumber());
        map.put("total", eventList.getTotalElements());
        map.put("eventList", eventVOS);
        return map;
    }
}
