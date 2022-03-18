package cn.com.goodlan.its.core.service.event.analyse;

import cn.com.goodlan.its.core.dao.primary.event.AnalyseRepository;
import cn.com.goodlan.its.core.pojo.query.AnalyseQuery;
import cn.com.goodlan.its.core.pojo.vo.AnalyseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyseServiceImpl implements AnalyseService {

    @Autowired
    private AnalyseRepository analyseRepository;

    @Override
    public List<AnalyseVo> search(AnalyseQuery query) {
        return analyseRepository.search(query);
    }

}
