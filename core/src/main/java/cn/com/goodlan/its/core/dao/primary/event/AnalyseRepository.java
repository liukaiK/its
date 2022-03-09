package cn.com.goodlan.its.core.dao.primary.event;

import cn.com.goodlan.its.core.pojo.query.AnalyseQuery;
import cn.com.goodlan.its.core.pojo.vo.AnalyseVo;

import java.util.List;

public interface AnalyseRepository {

    List<AnalyseVo> search(AnalyseQuery analyseQuery);

}
