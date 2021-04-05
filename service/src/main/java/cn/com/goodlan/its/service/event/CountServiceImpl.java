package cn.com.goodlan.its.service.event;

import cn.com.goodlan.its.dao.event.CountRepository;
import cn.com.goodlan.its.pojo.entity.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountServiceImpl implements CountService {

    @Autowired
    private CountRepository countRepository;

    @Override
    public Long queryCount(String licensePlateNumber) {
        Optional<Count> optional = countRepository.findById(licensePlateNumber);
        Count count;
        if (optional.isPresent()) {
            count = optional.get();
            count.setCount(count.getCount() + 1);
        } else {
            count = new Count();
            count.setCount(1L);
        }
        countRepository.save(count);
        return count.getCount();
    }
}
