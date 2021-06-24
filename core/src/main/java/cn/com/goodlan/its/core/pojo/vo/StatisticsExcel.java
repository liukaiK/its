package cn.com.goodlan.its.core.pojo.vo;

import cn.com.goodlan.its.common.annotations.Excel;

/**
 * @author liukai
 */
public class StatisticsExcel {

    /**
     * 单位名称
     */
    @Excel(name = "单位名称", sort = 1, defaultValue = "未知")
    private String collegeName;

    @Excel(name = "扣分", cellType = Excel.ColumnType.NUMERIC, sort = 2)
    private Long score;

    public StatisticsExcel() {
    }

    public StatisticsExcel(String collegeName, Long score) {
        this.collegeName = collegeName;
        this.score = score;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
