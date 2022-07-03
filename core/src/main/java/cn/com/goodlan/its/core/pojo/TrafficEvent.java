package cn.com.goodlan.its.core.pojo;

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
     * 车辆大小 小型车
     */
    private String m_VehicleSize;

    /**
     * 违法地点
     */
    private String m_IllegalPlace;

    /**
     * 事件时间
     */
    private Date m_Utc;

    /**
     * 大图
     */
    public String bigImage;

    /**
     * 车速
     */
    private int nSpeed;

    private String ip;

    @Override
    public String toString() {
        return "{" +
                "m_EventName='" + m_EventName + '\'' +
                ", m_PlateNumber='" + m_PlateNumber + '\'' +
                ", m_VehicleSize='" + m_VehicleSize + '\'' +
                ", m_IllegalPlace='" + m_IllegalPlace + '\'' +
                ", m_Utc=" + m_Utc +
                ", nSpeed=" + nSpeed +
                ", ip='" + ip + '\'' +
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
