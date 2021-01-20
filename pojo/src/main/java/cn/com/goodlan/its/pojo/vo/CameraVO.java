package cn.com.goodlan.its.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CameraVO {


    private String id;

    private String name;

    private String ip;

    private String factory;

    /**
     * 位置
     */
    private String position;

    private String regionId;

    private String regionName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
