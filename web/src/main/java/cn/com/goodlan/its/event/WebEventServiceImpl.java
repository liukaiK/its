package cn.com.goodlan.its.event;

import cn.com.goodlan.its.core.dao.primary.event.EventRepository;
import cn.com.goodlan.its.core.dao.primary.system.region.RegionRepository;
import cn.com.goodlan.its.core.exception.DataValidException;
import cn.com.goodlan.its.core.mapstruct.EventMapper;
import cn.com.goodlan.its.core.pojo.dto.EventDTO;
import cn.com.goodlan.its.core.pojo.entity.primary.Region;
import cn.com.goodlan.its.core.pojo.entity.primary.Vehicle;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Event;
import cn.com.goodlan.its.core.pojo.query.EventQuery;
import cn.com.goodlan.its.core.pojo.vo.EventVO;
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
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class WebEventServiceImpl implements WebEventService {

    private EventRepository eventRepository;

    private RegionRepository regionRepository;

    private EventMapper eventMapper;

    private HandlerManager handlerManager;


    @Override
    public Page<EventVO> search(EventQuery eventQuery, Pageable pageable) {
        Specification<Event> specification = querySpecification(eventQuery);
        Page<Event> page = eventRepository.findAll(specification, pageable);
        List<EventVO> list = eventMapper.convertList(page.getContent());
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }

    @Override
    public void create(EventDTO eventDTO) {

        Optional<Region> region = regionRepository.findById(eventDTO.getRegionId());

        if (!region.isPresent()) {
            throw new DataValidException("数据异常,不存在的区域id: " + eventDTO.getRegionId());
        }

        Vehicle vehicle = new Vehicle(eventDTO.getVehicleNumber(), eventDTO.getDriverName(), eventDTO.getDriverPhone(), eventDTO.getCollegeName(), eventDTO.getStudentNum());

        Event event = new Event(Event.Source.MANUAL);

        event.updateRegion(region.get());
        event.updatePlace(eventDTO.getPlace());
        event.updateVehicle(vehicle);
        event.updateSpeed(eventDTO.getSpeed());
        event.updateHappenTime(LocalDateTimeUtil.parse(eventDTO.getHappenTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        event.setVehicleColor(eventDTO.getVehicleColor());
        event.updateVehicleSize(eventDTO.getVehicleSize());
        event.updateImageUrl(eventDTO.getImageUrl());


        handlerManager.handler(event, eventDTO.getViolationTypeName());

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

    @Override
    public EventVO getById(String id) {
        Event event = eventRepository.getById(id);
        return eventMapper.convert(event);
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
                String licensePlateNumber = "%" + eventQuery.getVehicleNumber() + "%";
                list.add(criteriaBuilder.like(root.get("licensePlateNumber").as(String.class), licensePlateNumber.toUpperCase()));
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

}
