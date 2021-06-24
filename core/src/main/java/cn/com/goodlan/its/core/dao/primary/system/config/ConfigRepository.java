package cn.com.goodlan.its.core.dao.primary.system.config;

import cn.com.goodlan.its.core.dao.CustomizeRepository;
import cn.com.goodlan.its.core.pojo.entity.primary.Config;

public interface ConfigRepository extends CustomizeRepository<Config, String> {

    Config getByConfigKey(String configKey);

}
