package cn.com.goodlan.its.core.pojo.entity.secondary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 车辆黑名单中间表
 * <br>
 * 表名：tb_hit_back
 * <br>
 * 结构：
 * <br>
 * 字段名称	字段类型	长度	是否为空	说明	字段描述
 * vehplate	varchar	20	否		车牌号
 * remark	varchar	400	是		拉黑原因
 * backTime	datetime		否		拉黑时间
 * type	int		否		类型：1、新增；2、删除
 * flag	int		否		标记信息：传1即可
 *
 * <br>
 * 插入语句：
 * <br>
 * insert into tb_hit_back(vehplate,remark,backTime,type,flag) values('黑A88888','测试拉黑1111','2020-04-02 13:01:12',1,1);
 * 查询语句：
 * <br>
 * select vehplate,remark,backTime,type,flag from tb_hit_back
 *
 * @author liukai
 */
@Entity
@Table(name = "tb_hit_back")
public class HitBack {

    /**
     * 车牌号
     */
    private String vehplate;

    /**
     * 拉黑原因
     */
    private String remark;

    /**
     * 拉黑时间
     */
    private LocalDateTime backTime;

    /**
     * 类型：1、新增；2、删除
     */
    private Integer type = 1;

    /**
     * 标记信息：传1即可
     */
    private Integer flag = 1;

    @Id
    public String getVehplate() {
        return vehplate;
    }

    public void setVehplate(String vehplate) {
        this.vehplate = vehplate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getBackTime() {
        return backTime;
    }

    public void setBackTime(LocalDateTime backTime) {
        this.backTime = backTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
