package cn.com.goodlan.its.core.dao.primary.whitelist;

import cn.com.goodlan.its.core.dao.CustomizeRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.whitelist.Whitelist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WhitelistRepository extends CustomizeRepository<Whitelist, String> {

    Page<Whitelist> findByLicensePlateNumber(String licensePlateNumber, Pageable pageable);

    Whitelist findByLicensePlateNumber(String licensePlateNumber);

}
