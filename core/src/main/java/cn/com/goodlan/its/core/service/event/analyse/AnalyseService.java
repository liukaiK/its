package cn.com.goodlan.its.core.service.event.analyse;

import cn.com.goodlan.its.core.pojo.query.AnalyseQuery;
import cn.com.goodlan.its.core.pojo.vo.AnalyseVo;

import java.util.List;

public interface AnalyseService {

    List<AnalyseVo> search(AnalyseQuery query);


}
