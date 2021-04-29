package cn.com.goodlan.its.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TrafficEvent {

    /**
     * 事件名称
     */
    private String m_EventName;

    /**
     * 车牌号
     */
    private String m_PlateNumber;

    /**
     * 车牌类型
     */
    private String m_PlateType;

    /**
     * 车牌颜色 Other
     */
    private String m_PlateColor;

    /**
     * 车身颜色 Black
     */
    private String m_VehicleColor;

    /**
     * 车身类型  例Motorcycle
     */
    private String m_VehicleType;

    /**
     * 车辆大小 小型车
     */
    private String m_VehicleSize;

    /**
     * 违法地点
     */
    private String m_IllegalPlace;

    /**
     * 通道号
     */
    private String m_LaneNumber;

    /**
     * 事件时间
     */
    private Date m_Utc;

    /**
     * 大图
     */
    public String bigImage;

    /**
     * 车牌图
     */
//    public String plateImage;

    /**
     * 车速
     */
    private int nSpeed;

    private String ip;

    /**
     * 限速范围
     */
    private int nUpperSpeedLimit;

    @Override
    public String toString() {
        return "TrafficEvent{" +
                "m_EventName='" + m_EventName + '\'' +
                ", m_PlateNumber='" + m_PlateNumber + '\'' +
                ", m_PlateType='" + m_PlateType + '\'' +
                ", m_PlateColor='" + m_PlateColor + '\'' +
                ", m_VehicleColor='" + m_VehicleColor + '\'' +
                ", m_VehicleType='" + m_VehicleType + '\'' +
                ", m_VehicleSize='" + m_VehicleSize + '\'' +
                ", m_IllegalPlace='" + m_IllegalPlace + '\'' +
                ", m_LaneNumber='" + m_LaneNumber + '\'' +
                ", m_Utc=" + m_Utc +
                ", nSpeed=" + nSpeed +
                ", ip='" + ip + '\'' +
                ", nUpperSpeedLimit=" + nUpperSpeedLimit +
                '}';
    }

//    // 文件总数
//    private String m_FileCount;
//
//    // 文件编号
//    private String m_FileIndex;
//
//    // 组ID
//    private String m_GroupID;


//    // 车牌对应信息，BOOL类型
//    private int m_bPicEnble;
//    // 车牌偏移量
//    private int m_OffSet;
//    // 文件大小
//    private int m_FileLength;


}
