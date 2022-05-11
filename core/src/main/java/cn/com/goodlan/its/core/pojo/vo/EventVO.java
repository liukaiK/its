package cn.com.goodlan.its.core.pojo.vo;

import cn.com.goodlan.its.core.annotations.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author liukai
 */
@Setter
@Getter
@ToString
@ApiModel(description = "事件")
public class EventVO {

    private String id;

    /**
     * 车牌号码
     */
    @Excel(name = "车牌号", sort = 1)
    @ApiModelProperty(value = "车牌号")
    private String vehicleNumber;

    /**
     * 车主姓名
     */
    @Excel(name = "车主姓名", sort = 2)
    @ApiModelProperty(value = "车主姓名")
    private String driverName;

    /**
     * 车主电话
     */
    @ApiModelProperty(value = "车主电话")
    @Excel(name = "车主电话", cellType = Excel.ColumnType.STRING, sort = 3)
    private String driverPhone;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "所属单位")
    @Excel(name = "所属单位", defaultValue = "未知", sort = 4)
    private String bmmc;


    @Excel(name = "次序", cellType = Excel.ColumnType.STRING, sort = 5)
    private String num;

    /**
     * 违规分类
     */
    @ApiModelProperty(value = "违规分类")
    @Excel(name = "违规分类", cellType = Excel.ColumnType.STRING, sort = 6)
    private String violationName;

    /**
     * 扣了多少分
     */
    @ApiModelProperty(value = "扣了多少分")
    @Excel(name = "扣分", cellType = Excel.ColumnType.STRING, sort = 7)
    private String score;

    @ApiModelProperty(value = "违规时间")
    @Excel(name = "违规时间", dateFormat = "yyyy-MM-dd HH:mm:ss", sort = 8)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    /**
     * 违规地点
     */
    @ApiModelProperty(value = "违规地点")
    @Excel(name = "违规地点", sort = 9)
    private String place;

    private String regionName;

    private String laneNumber;

    private String vehicleColor;

    private String vehicleSize;

    private Integer speed;

    @ApiModelProperty(value = "图片地址")
    private String imageUrl;

}
