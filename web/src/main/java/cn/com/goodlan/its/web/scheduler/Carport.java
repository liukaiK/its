package cn.com.goodlan.its.web.scheduler;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Carport {

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
