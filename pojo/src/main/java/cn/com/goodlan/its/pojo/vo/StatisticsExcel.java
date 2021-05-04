package cn.com.goodlan.its.pojo.vo;

import cn.com.goodlan.its.common.annotations.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liukai
 */
@Data
@AllArgsConstructor
public class StatisticsExcel {

    /**
     * 单位名称
     */
    @Excel(name = "单位名称", sort = 1, defaultValue = "未知")
    private String collegeName;

    @Excel(name = "扣分", cellType = Excel.ColumnType.NUMERIC, sort = 2)
    private Long score;


}
