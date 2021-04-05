package cn.com.goodlan.its.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecordVO {

    private String id;

    /**
     * 姓名
     */
    private String name;

    private String regionId;

    private String regionName;

    private String vehicleNumber;


    private String violationId;

    private String bmmc;

    private String place;

    /**
     * 违规类型
     */
    private String violationName;

    /**
     * 违规次数
     */
    private Long count;

    private LocalDateTime time;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


}
