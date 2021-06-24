package cn.com.goodlan.its.core.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 存放在SpringSecurity中的实体部门对象
 *
 * @author liukai
 */
@Data
@ToString
public class SecurityDeptBean implements Serializable {

    private String id;

    private String deptName;


}
