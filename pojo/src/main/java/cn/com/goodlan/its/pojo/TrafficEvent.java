package cn.com.goodlan.its.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
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


//    // 文件总数
//    private String m_FileCount;
//
//    // 文件编号
//    private String m_FileIndex;
//
//    // 组ID
//    private String m_GroupID;

//
//    // 通道号
//    private String m_LaneNumber;
//    // 车牌对应信息，BOOL类型
//    private int m_bPicEnble;
//    // 车牌偏移量
//    private int m_OffSet;
//    // 文件大小
//    private int m_FileLength;
//    // 事件时间
//    private Date m_Utc;
//    //车速
//    private int nSpeed;
//    //限速范围
//    private int nUpperSpeedLimit;
//
//    /**
//     * 大图
//     */
//    public String bigImage;
//    /**
//     * 车牌图
//     */
//    public String plateImage;

}
