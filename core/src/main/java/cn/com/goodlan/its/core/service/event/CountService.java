package cn.com.goodlan.its.core.service.event;

public interface CountService {

    /**
     * 查询车辆违规次数
     *
     * @param licensePlateNumber 车牌号
     * @param violationName      违规类型
     * @return 违规次数
     */
    Long queryCount(String licensePlateNumber, String violationName);


}
