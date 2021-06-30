package cn.com.goodlan.its.core.pojo;

import cn.com.goodlan.its.core.pojo.entity.primary.College;
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
public class SecurityCollegeBean implements Serializable {

    private String id;

    private String collegeName;

    private SecurityCollegeBean(College college) {
        this.id = college.getId();
        this.collegeName = college.getName();
    }

    public static SecurityCollegeBean convertFromCollege(College college) {
        return new SecurityCollegeBean(college);
    }


}
