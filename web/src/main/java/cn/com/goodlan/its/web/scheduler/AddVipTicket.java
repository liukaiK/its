package cn.com.goodlan.its.web.scheduler;

import cn.com.goodlan.its.core.pojo.entity.primary.Vehicle;
import cn.hutool.core.date.DateUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
public class AddVipTicket implements Serializable {

    /**
     * 停车场编号
     */
    private final String parkCode = "2KPLOXLG";

    /**
     * VIP类型唯一编号
     */
    private final String vipTypeCode = "T99";

    /**
     * VIP类型名称，如果没有对接“新增月卡类型”接口，可以在停车场管理系统预先建好VIP类型，然后按“VIP类型名称”开通VIP票
     */
    private final String vipTypeName = "测试月卡类型99";

    /**
     * 外部系统定义的票唯一编号
     */
    private final String ticketNo = "Test99";

    /**
     * 车主
     */
    private String carOwner = "张三";

    /**
     * 车主电话
     */
    private String telphone = "18022222222";

    /**
     * 车主性别，0男 1女 2未知
     */
    private final String sex = "0";

    /**
     * 操作员
     */
    private final String operator = "test";

    /**
     * 操作时间，yyyy-MM-dd HH:mm:ss
     */
    private String operateTime = "2017-02-28 00:00:00";

    /**
     * 票原价，单位元
     */
    private final String originPrice = "200";

    /**
     * 票折后价，单位元
     */
    private final String favorPrice = "200";

    /**
     * 开通值，时间类型，填月数；余额类型，填储值，openValue和timePeriodList不能同时为空
     */
    private final String openValue = "1.00";

    /**
     * 车位数
     */
    private final String openCarCount = "100";

    /**
     * 车辆列表
     */
    private Car[] carList;

    /**
     * 有效时间段列表，openValue和timePeriodList不能同时为空；非时间类型VIP有效时间段不能为空
     */
    private TimePeriod[] timePeriodList;

    /**
     * 车位列表
     */
    private Carport[] carportList;

    public static AddVipTicket convertFromVehicle(Vehicle vehicle) {
        AddVipTicket addVipTicket = new AddVipTicket();
        addVipTicket.setCarOwner(vehicle.getDriverName());
        addVipTicket.setTelphone(vehicle.getDriverPhone());
        addVipTicket.setCarList(new Car[]{new Car(vehicle.getLicensePlateNumber())});
        addVipTicket.setOperateTime(DateUtil.formatDateTime(new Date()));
        addVipTicket.setCarportList(new Carport[]{new Carport(addVipTicket.getParkCode())});
        addVipTicket.setTimePeriodList(new TimePeriod[]{new TimePeriod()});
        return addVipTicket;
    }


}
