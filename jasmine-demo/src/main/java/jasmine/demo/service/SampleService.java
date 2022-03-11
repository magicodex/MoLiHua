package jasmine.demo.service;

import jasmine.core.util.QErrorUtil;
import jasmine.framework.cache.CacheUtil;
import jasmine.framework.lock.annotation.DistributedLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author mh.z
 */
@Service
public class SampleService {
    private static Logger logger = LoggerFactory.getLogger(SampleService.class);

    /**
     * 加锁
     *
     * @param lockName
     * @param lockTime
     */
    @DistributedLock(category = "sample", key = "#lockName")
    public void lock(String lockName, Long lockTime) {
        try {
            Thread.sleep(lockTime);
        } catch (InterruptedException e) {
            throw QErrorUtil.sneakyError(e);
        }
    }

    /**
     * 读取缓存
     *
     * @param name
     * @return
     */
    public String getFromCache(String name) {
        return CacheUtil.get("sample", name, String.class);
    }

    /**
     * 添加缓存
     *
     * @param name
     * @param value
     */
    public void setToCache(String name, String value) {
        CacheUtil.set("sample", name, value);
    }

}
