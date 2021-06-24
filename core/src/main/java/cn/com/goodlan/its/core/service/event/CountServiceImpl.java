package cn.com.goodlan.its.core.service.event;

import cn.com.goodlan.its.dao.primary.event.CountRepository;
import cn.com.goodlan.its.pojo.entity.primary.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountServiceImpl implements CountService {

    @Autowired
    private CountRepository countRepository;

    @Override
    public Long queryCountAndSave(String licensePlateNumber) {
        Optional<Count> optional = countRepository.findById(licensePlateNumber);
        Count count;
        if (optional.isPresent()) {
            count = optional.get();
            count.setCount(count.getCount() + 1);
        } else {
            count = new Count();
            count.setLicensePlateNumber(licensePlateNumber);
            count.setCount(1L);
        }
        countRepository.save(count);
        return count.getCount();
    }
}
