package cn.com.goodlan.its.web.scheduler;

import cn.com.goodlan.its.core.dao.primary.system.vehicle.VehicleRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.Vehicle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liukai
 */
@Component
public class Scheduler {

    private static final Log logger = LogFactory.getLog(Scheduler.class);

    @Autowired
    private VehicleRepository vehicleRepository;


    /**
     * 每分钟执行一次
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void statusCheck() {
        logger.info("每分钟执行一次。开始……");
        //statusTask.healthCheck();
//        List<Vehicle> vehicles = vehicleRepository.findAll();



        logger.info("每分钟执行一次。结束。");
    }


}
