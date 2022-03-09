package cn.com.goodlan.its.core.pojo.vo;

import cn.com.goodlan.its.core.annotations.Excel;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnalyseVo {

    @Excel(name = "单位名称", sort = 1)
    private String collageName;

    @Excel(name = "超速1", suffix = "分", sort = 2)
    private int speedOne;

    @Excel(name = "超速2", suffix = "分", sort = 3)
    private int speedTwo;

    @Excel(name = "超速3", suffix = "分", sort = 4)
    private int speedThree;

    @Excel(name = "违停", suffix = "分", sort = 5)
    private int stop;

    @Excel(name = "合计", suffix = "分", sort = 6)
    private int sum;


}
