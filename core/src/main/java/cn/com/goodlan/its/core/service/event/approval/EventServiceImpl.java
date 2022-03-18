package cn.com.goodlan.its.core.service.event.approval;

import cn.com.goodlan.its.core.dao.primary.event.EventRepository;
import cn.com.goodlan.its.core.mapstruct.EventMapper;
import cn.com.goodlan.its.core.pojo.Params;
import cn.com.goodlan.its.core.pojo.dto.EventDTO;
import cn.com.goodlan.its.core.pojo.entity.primary.Vehicle;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import cn.com.goodlan.its.core.pojo.vo.EventVO;
import cn.com.goodlan.its.core.service.system.vehicle.VehicleService;
import cn.hutool.core.convert.Convert;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    private VehicleService vehicleService;

    private EventMapper eventMapper;

    @Override
    public Page<EventVO> search(EventDTO eventDTO, Pageable pageable) {
        Specification<Event> specification = querySpecification(eventDTO);
        Page<Event> page = eventRepository.findAll(specification, pageable);
        List<EventVO> list = eventMapper.convertList(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public List<EventVO> export(EventDTO eventDTO) {
        Specification<Event> specification = querySpecification(eventDTO);
        List<Event> list = eventRepository.findAll(specification);
        return eventMapper.convertList(list);
    }

    @Override
    public void remove(String ids) {
        String[] eventIds = Convert.toStrArray(ids);
        for (String id : eventIds) {
            Event event = eventRepository.getOne(id);
            event.remove();

            String violationName = event.getViolationName();
            if (violationName.startsWith("超速")) {
                violationName = "超速";
            } else {
                violationName = "违章停车";
            }


            LocalDateTime firstDay = LocalDateTime.of(event.getTime().toLocalDate().with(TemporalAdjusters.firstDayOfYear()), LocalTime.MIN);

            LocalDateTime lastDay = LocalDateTime.of(event.getTime().toLocalDate().with(TemporalAdjusters.lastDayOfYear()), LocalTime.MAX);

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
            event.setNum(num);
            num++;
        }
        for (Event event : list) {
            if (event.getNum() == 1) {
                if (event.getScoreId().isScore1() || event.getScoreId().isScore2() || event.getScoreId().isStop()) {
                    event.updateScore(0);
                } else {
                    event.updateScore(event.getScoreId());
                }
            }
        }
    }

    private Specification<Event> querySpecification(EventDTO eventDTO) {
        return (root, query, criteriaBuilder) -> {
            if (!query.getResultType().equals(Long.class)) {
                root.fetch("camera", JoinType.LEFT);
            }
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(eventDTO.getCollegeName())) {
                list.add(criteriaBuilder.like(root.get("collegeName").as(String.class), eventDTO.getCollegeName() + "%"));
            }
            if (StringUtils.isNotEmpty(eventDTO.getViolationTypeId())) {
                list.add(criteriaBuilder.equal(root.get("violationId").as(String.class), eventDTO.getViolationTypeId()));
            }
            if (StringUtils.isNotEmpty(eventDTO.getDriverName())) {
                list.add(criteriaBuilder.like(root.get("driverName").as(String.class), eventDTO.getDriverName() + "%"));
            }
            if (StringUtils.isNotEmpty(eventDTO.getVehicleNumber())) {
                list.add(criteriaBuilder.like(root.get("licensePlateNumber").as(String.class), eventDTO.getVehicleNumber() + "%"));
            }
            if (StringUtils.isNotEmpty(eventDTO.getStartTime())) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("time").as(String.class), eventDTO.getStartTime()));
            }
            if (StringUtils.isNotEmpty(eventDTO.getEndTime())) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("time").as(String.class), eventDTO.getEndTime()));
            }
            list.add(criteriaBuilder.equal(root.get("deleted").as(Event.Deleted.class), Event.Deleted.NORMAL));
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        };
    }

    @Override
    public EventVO getById(String id) {
        Event event = eventRepository.getOne(id);
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
