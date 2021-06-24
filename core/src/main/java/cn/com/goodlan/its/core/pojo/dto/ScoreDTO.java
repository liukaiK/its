package cn.com.goodlan.its.core.pojo.dto;

import lombok.Data;

@Data
public class ScoreDTO {

    private String id;

    private String name;

    private Integer number;

    private String violationId;

    private String regionId;

    private String remark;

    /**
     * 分值最小值
     */
    private Integer minRange;


    /**
     * 分值最大值
     */
    private Integer maxRange;

}
