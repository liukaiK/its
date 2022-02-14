package cn.com.goodlan.its.core.service.whitelist;

import cn.com.goodlan.its.core.dao.primary.whitelist.WhitelistRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.event.Whitelist;
import cn.com.goodlan.its.core.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author liukai
 */
@Service
@AllArgsConstructor
public class WhitelistService {

    private WhitelistRepository whitelistRepository;


    public void save(String licensePlateNumber) {
        Whitelist whitelist = new Whitelist();
        whitelist.setLicensePlateNumber(licensePlateNumber);
        whitelistRepository.save(whitelist);
    }


    public void remove(String licensePlateNumber) {
        whitelistRepository.deleteById(licensePlateNumber);
    }

    public Page<Whitelist> search(String licensePlateNumber, Pageable pageable) {
        if (StringUtils.isNotEmpty(licensePlateNumber)) {
            return whitelistRepository.findByLicensePlateNumber(licensePlateNumber, pageable);
        }
        return whitelistRepository.findAll(pageable);
    }

}
