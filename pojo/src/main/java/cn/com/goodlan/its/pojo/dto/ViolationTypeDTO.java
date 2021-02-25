package cn.com.goodlan.its.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * description:
 *
 * @author 王硕
 * @date 2021/1/18-16:57
 */
@Data
public class ViolationTypeDTO {

    private String id;

    /**
     * 违规分类
     */
    @NotBlank(message = "分类名称不能为空")
    private String name;

    /**
     * 违规编码
     */
    private String code;

    private String remark;

}
