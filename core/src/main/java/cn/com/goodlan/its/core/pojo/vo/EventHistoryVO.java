package cn.com.goodlan.its.core.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class EventHistoryVO {

    private String id;

    private String eventName;

    /**
     * 车主姓名
     */
    private String driverName;

    /**
     * 车牌号
     */
    private String licensePlateNumber;

    /**
     * 车主电话
     */
    private String driverPhone;


    /**
     * 违规地点
     */
    private String place;

    /**
     * 事件图片url
     */
    private String imageUrl;

    private String collegeName;

    /**
     * 车速
     */
    private Integer speed;

    private String ip;

    private String size;

    private String result;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime happenTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
