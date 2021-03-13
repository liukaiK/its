package cn.com.goodlan.its.web.sms;

import cn.com.goodlan.its.web.properties.SmsProperties;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private ObjectMapper objectMapper;

    String message1 = "您好，您的车辆 XXXX（车牌号）于2021年3月13日11:25:39（违规时间）在XXX（违规地点）超速/违停（违规分类），系统对您于警告处理。";

    String message2 = "您好，您的车辆 XXXX（车牌号）于2021年3月13日11:25:39（违规时间）在XXX（违规地点）超速/违停（违规分类），扣分XX（分）。您已经尾骨超过4次被系统拉黑。";

    String message3 = "您好，您的车辆 XXXX（车牌号）于2021年3月13日11:25:39（违规时间）在XXX（违规地点）超速/违停（违规分类），扣分XX（分）。违规超过4次将被进行拉黑处理。";


    @Autowired
    private SmsProperties smsProperties;


    @Override
    public void sendSms(String mobilePhone, String content) throws JsonProcessingException {
        SmsPar smsPar = new SmsPar(mobilePhone, content);
        try {
            HttpUtil.post(smsProperties.getSendSmsUrl(), objectMapper.writeValueAsString(smsPar), 10);
        } catch (JsonProcessingException e) {
            log.error("发送短信出现异常", e);
        }
    }

}
