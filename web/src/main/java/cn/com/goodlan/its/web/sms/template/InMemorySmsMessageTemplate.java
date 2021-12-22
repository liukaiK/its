package cn.com.goodlan.its.web.sms.template;

import org.springframework.stereotype.Service;

@Service
public class InMemorySmsMessageTemplate implements SmsMessageTemplate {

    private final String message = "您的车辆{0}于{1}在{2}，被交通技术监控设备记录了{3}的违法行为。给予{4}处罚，请知悉。详情请登录哈工大APP进行查询。目前为宣传教育阶段，自2022年1月1日起正式实施。";

    @Override
    public String getSmsTemplate() {
        return message;
    }

}
