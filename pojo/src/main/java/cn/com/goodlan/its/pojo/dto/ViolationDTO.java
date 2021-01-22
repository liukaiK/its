package cn.com.goodlan.its.pojo.dto;

import lombok.Data;

/**
 * description:
 *
 * @author: 王硕
 * @date: 2021/1/18-16:57
 */
@Data
public class ViolationDTO {

    private String id;

    /**
     * 违规分类
     */
    private String name;
    /**
     * 违规编码
     */
    private String number;
}