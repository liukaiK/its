package cn.com.goodlan.its.core.dao.primary.event;

import cn.com.goodlan.its.core.pojo.query.AnalyseQuery;
import cn.com.goodlan.its.core.pojo.vo.AnalyseVo;
import cn.com.goodlan.its.core.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnalyseRepositoryImpl implements AnalyseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AnalyseVo> search(AnalyseQuery analyseQuery) {

        if (StringUtils.isEmpty(analyseQuery.getEndTime())) {
            analyseQuery.setEndTime("9999-12:12 23:59:59");
        }

        String sql = "select event.college_name as collageName,\n" +
                "       (select sum(score) from eve_event event1 where violation_name = '超速1' and time >= ? and time <= ? and status = 1 and event.college_name = event1.college_name)  as speed1,\n" +
                "       (select sum(score) from eve_event event2 where violation_name = '超速2' and time >= ? and time <= ? and status = 1 and event.college_name = event2.college_name)  as speed2,\n" +
                "       (select sum(score) from eve_event event3 where violation_name = '超速3' and time >= ? and time <= ? and status = 1 and event.college_name = event3.college_name)  as speed3,\n" +
                "       (select sum(score) from eve_event event4 where violation_name = '违章停车' and time >= ? and time <= ? and status = 1 and event.college_name = event4.college_name) as stop\n" +
                "from eve_event event\n" + "where event.college_name is not null\n" + "group by event.college_name";

        List<AnalyseVo> list = jdbcTemplate.query(sql, (rs, rowNum) -> {
            String collageName = rs.getString("collageName");
            String speed1 = rs.getString("speed1");
            String speed2 = rs.getString("speed2");
            String speed3 = rs.getString("speed3");
            String stop = rs.getString("stop");
            if (StringUtils.isEmpty(speed1)) {
                speed1 = "0";
            }
            if (StringUtils.isEmpty(speed2)) {
                speed2 = "0";
            }
            if (StringUtils.isEmpty(speed3)) {
                speed3 = "0";
            }
            if (StringUtils.isEmpty(stop)) {
                stop = "0";
            }
            return AnalyseVo.builder().collageName(collageName).speedOne(Integer.parseInt(speed1)).speedTwo(Integer.parseInt(speed2)).speedThree(Integer.parseInt(speed3)).stop(Integer.parseInt(stop)).sum(Integer.parseInt(speed1) + Integer.parseInt(speed2) + Integer.parseInt(speed3) + Integer.parseInt(stop)).build();
        },
                analyseQuery.getStartTime(),
                analyseQuery.getEndTime(),
                analyseQuery.getStartTime(),
                analyseQuery.getEndTime(),
                analyseQuery.getStartTime(),
                analyseQuery.getEndTime(),
                analyseQuery.getStartTime(),
                analyseQuery.getEndTime());


        return list;

    }


}
