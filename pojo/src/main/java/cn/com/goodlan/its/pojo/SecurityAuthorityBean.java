package cn.com.goodlan.its.pojo;

import cn.com.goodlan.its.pojo.entity.Menu;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

/**
 * 存放在SpringSecurity中的实体角色对象
 *
 * @author liukai
 */
@Data
@ToString
public class SecurityAuthorityBean implements GrantedAuthority {

    private String id;

    private String authority;

    public SecurityAuthorityBean() {

    }

    public SecurityAuthorityBean(Menu menu) {
        this.id = menu.getId();
        this.authority = menu.getAuthority();
    }

    @Override
    public String getAuthority() {
        return authority;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        SecurityAuthorityBean authority = (SecurityAuthorityBean) obj;
        return this.id.equals(authority.getId());
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
