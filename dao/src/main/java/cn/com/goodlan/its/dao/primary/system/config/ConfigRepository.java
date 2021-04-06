package cn.com.goodlan.its.dao.primary.system.config;

import cn.com.goodlan.its.dao.CustomizeRepository;
import cn.com.goodlan.its.pojo.entity.primary.Config;

public interface ConfigRepository extends CustomizeRepository<Config, String> {

    Config getByConfigKey(String configKey);

}
