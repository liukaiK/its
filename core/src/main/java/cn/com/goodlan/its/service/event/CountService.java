package cn.com.goodlan.its.service.event;

public interface CountService {

    /**
     * 查询车辆违规次数
     *
     * @param licensePlateNumber 车牌号
     * @return 违规次数
     */
    Long queryCountAndSave(String licensePlateNumber);


}
