package cn.com.goodlan.its.web.scheduler;

public class Car {

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

    public Car(String carNo) {
        this.carNo = carNo;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carNo='" + carNo + '\'' +
                ", carLicenseType='" + carLicenseType + '\'' +
                ", carColor='" + carColor + '\'' +
                ", carLogo='" + carLogo + '\'' +
                ", carModel='" + carModel + '\'' +
                ", carType='" + carType + '\'' +
                ", carOwnType='" + carOwnType + '\'' +
                ", carSizeType='" + carSizeType + '\'' +
                '}';
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getCarLicenseType() {
        return carLicenseType;
    }


    public String getCarColor() {
        return carColor;
    }


    public String getCarLogo() {
        return carLogo;
    }


    public String getCarModel() {
        return carModel;
    }


    public String getCarType() {
        return carType;
    }

    public String getCarOwnType() {
        return carOwnType;
    }


    public String getCarSizeType() {
        return carSizeType;
    }


}
