package cn.com.goodlan.its.web.rabbit;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class VehicleCacheImpl implements VehicleCache {

    private static final Logger log = LoggerFactory.getLogger(VehicleCacheImpl.class);


    private final static TimedCache<String, String> timeCache = CacheUtil.newTimedCache(5 * 1000);

    static {
        timeCache.schedulePrune(5 * 1000);
    }

    @Override
    public void put(String licensePlateNumber) {
        timeCache.put(licensePlateNumber, licensePlateNumber);
    }

    @Override
    public boolean exists(String licensePlateNumber) {
        return timeCache.containsKey(licensePlateNumber);
    }

}
