package cn.com.goodlan.its.web.scheduler;

import cn.com.goodlan.its.core.pojo.entity.primary.Vehicle;
import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 单例
 *
 * @author liukai
 */
@Data
public class RequestBody implements Serializable {

    private final static RequestBody INSTANCE = new RequestBody();

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
    private final String operator = "交通信息平台";

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

    private RequestBody() {

    }

    public static RequestBody from(Vehicle vehicle) {
        INSTANCE.setCarOwner(vehicle.getDriverName());
        INSTANCE.setTelphone(vehicle.getDriverPhone());
        INSTANCE.setCarList(new Car[]{Car.getInstance(vehicle.getLicensePlateNumber())});
        INSTANCE.setOperateTime(DateUtil.formatDateTime(new Date()));
        INSTANCE.setCarportList(new Carport[]{new Carport(INSTANCE.getParkCode())});
        INSTANCE.setTimePeriodList(new TimePeriod[]{new TimePeriod()});
        return INSTANCE;
    }


}

@Data
class Car {

    private static Car instance = new Car();

    /**
     * 车牌号，大写
     */
    private String carNo;

    /**
     * 车牌类型
     */
    private final String carLicenseType = "";

    /**
     * 车辆颜色
     */
    private final String carColor = "";

    /**
     * 车辆品牌
     */
    private final String carLogo = "";

    /**
     * 品牌型号
     */
    private final String carModel = "";

    /**
     * 车辆类型
     */
    private final String carType = "";

    /**
     * 车归属类型，0未知，1公车，2私车
     */
    private final String carOwnType = "";

    /**
     * 车辆大小类型，0未知，1小车，2大车，3超大车，4中型车
     */
    private final String carSizeType = "";

    private Car() {

    }

    public static Car getInstance(String carNo) {
        instance.setCarNo(carNo);
        return instance;
    }

}

@Data
class Carport {

    /**
     * 停车场编号
     */
    private String parkingLotId = "";

    /**
     * 车位编号
     */
    private String carportSerialNumber = "1";

    /**
     * 车位别名
     */
    private String carportAlaisName = "1";

    /**
     * 车位类型，0未指定,1固定车位,2临时车位,3租用车位
     */
    private String carportType = "1";

    /**
     * 是否开启预订，0否，1是
     */
    private String isOpenReserved = "1";

    public Carport(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
}

@Data
class TimePeriod {

    /**
     * 起始时间，yyyy-MM-dd HH:mm:ss
     */
    private String startTime;

    /**
     * 起始时间，yyyy-MM-dd HH:mm:ss
     */
    private String endTime;


    public TimePeriod() {
        Date date = new Date();
        this.startTime = DateUtil.formatDateTime(date);
        this.endTime = DateUtil.offsetMonth(date, 12).toString();
    }

}