package cn.com.goodlan.its.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * description:
 *
 * @author 王硕
 * @date 2021/1/18-16:56
 */
@Data
public class ViolationTypeVO {


    private String id;

    /**
     * 违规分类
     */
    private String name;

    /**
     * 违规编码
     */
    private String code;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
