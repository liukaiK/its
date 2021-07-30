package cn.com.goodlan.its.web.scheduler;

import cn.com.goodlan.its.core.dao.primary.system.vehicle.VehicleRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.Vehicle;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * 文档地址 http://openydt.yidianting.xin/Api/guide
 *
 * @author liukai
 */
@Component
public class Scheduler {

    private static final Log logger = LogFactory.getLog(Scheduler.class);

    private final static String URL = "https://open.yidianting.xin/openydt/api/v2/addVipTicket?sign=%s";

    private final static String KEY = "jrvzhr";

    private final static String SECRET = "z16lw7wy7z4jxbajid5ecegdres3vdgf";

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * 每天凌晨1点执行一次
     */
//    @Scheduled(cron = "0 0 1 * * ?")
    public void statusCheck() {
        logger.info("开始推送车辆数据到停车场");
        List<Vehicle> vehicles = vehicleRepository.findAll();
        for (Vehicle vehicle : vehicles) {
            push(vehicle);
        }
        logger.info("推送车辆结束");
    }

    /**
     * 推送车辆到停车场
     */
    private void push(Vehicle vehicle) {
        String time = DateUtil.format(new Date(), "yyyyMMddHHmmss");

        String sign = getSign(time);

        RequestBody addVipTicket = RequestBody.from(vehicle);


        try {
            HttpResponse response = HttpRequest.post(getUrl(sign))
                    .auth(getAuth(time))
                    .body(objectMapper.writeValueAsString(addVipTicket))
                    .timeout(2000)
                    .execute().charset(StandardCharsets.UTF_8);
            if (!response.isOk()) {
                logger.error("调用接口出现异常 " + response.body());
            }
        } catch (JsonProcessingException | HttpException e) {
            logger.error(vehicle.getLicensePlateNumber() + " 推送超时!");
        }

    }


    /**
     * sign=MD5加密(key+冒号+时间+冒号+密码)，加密后的值应转换为32位小写字符串，
     * 如MD5("abc:20170416142030:123456") = c2d404708a925d563fb794929e04f36c
     */
    private String getSign(String yyyyMMddHHmmss) {
        return DigestUtil.md5Hex(KEY + ":" + yyyyMMddHHmmss + ":" + SECRET);
    }

    private String getAuth(String yyyyMMddHHmmss) {
        return Base64.encode(KEY + ":" + yyyyMMddHHmmss);
    }

    private String getUrl(String sign) {
        return String.format(URL, sign);
    }


}
