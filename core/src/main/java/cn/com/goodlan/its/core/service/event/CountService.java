package cn.com.goodlan.its.core.service.event;

public interface CountService {

    /**
     * 查询今年车辆的违规次数 从今年年初1月1号开始计算
     *
     * @param licensePlateNumber 车牌号
     * @param violationName      违规类型 是总类型 超速和违章停车
     * @return 违规次数
     */
    Long queryCountThisYear(String licensePlateNumber, String violationName);


}
