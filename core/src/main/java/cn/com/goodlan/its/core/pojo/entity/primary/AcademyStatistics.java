package cn.com.goodlan.its.core.pojo.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademyStatistics {
    /**
     * 学院名称
     */
    private String name;
    /**
     * 违章次数
     */
    private String num;
    /**
     * 最新违章时间
     */
    private String latestTime;
}
