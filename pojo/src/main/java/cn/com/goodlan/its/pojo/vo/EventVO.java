package cn.com.goodlan.its.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventVO {

    private String id;

    private String name;

    /**
     * 车牌号码
     */
    private String vehicleNumber;

    /**
     * 学院名称
     */
    private String collegeName;


    private String regionName;

    /**
     * 违规分类
     */
    private String violationName;

    private Integer status;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
