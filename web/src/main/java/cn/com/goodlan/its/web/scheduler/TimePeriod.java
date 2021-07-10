package cn.com.goodlan.its.web.scheduler;

import cn.hutool.core.date.DateUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class TimePeriod {

    /**
     * 起始时间，yyyy-MM-dd HH:mm:ss
     */
    private String startTime;

    /**
     * 起始时间，yyyy-MM-dd HH:mm:ss
     */
    private String endTime;


    public TimePeriod() {
        Date date = new Date();
        this.startTime = DateUtil.formatDateTime(date);
        this.endTime = DateUtil.offsetMonth(date, 12).toString();
    }

}
