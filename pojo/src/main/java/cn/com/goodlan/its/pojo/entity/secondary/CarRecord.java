package cn.com.goodlan.its.pojo.entity.secondary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 车辆进出校园记录
 */
@Entity
@Table(name = "tb_car_record")
public class CarRecord {

    /**
     * 车牌号
     */
    private String veplate;

    /**
     * 进校/出校时间
     */
    private LocalDateTime operateTime;

    /**
     * 进校/出校标志
     */
    private String inOutType;

    /**
     * 进校/出校地点
     */
    private String gateName;

    /**
     * 记录更新时间
     */
    private LocalDateTime updateTime;

    @Id
    public String getVeplate() {
        return veplate;
    }

    public void setVeplate(String veplate) {
        this.veplate = veplate;
    }

    public LocalDateTime getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(LocalDateTime operateTime) {
        this.operateTime = operateTime;
    }

    public String getInOutType() {
        return inOutType;
    }

    public void setInOutType(String inOutType) {
        this.inOutType = inOutType;
    }

    public String getGateName() {
        return gateName;
    }

    public void setGateName(String gateName) {
        this.gateName = gateName;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
