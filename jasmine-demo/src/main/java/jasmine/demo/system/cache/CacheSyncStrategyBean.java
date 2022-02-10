package jasmine.demo.system.cache;

import jasmine.core.context.InitSupport;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.cache.CacheService;
import jasmine.framework.cache.CacheSyncStrategy;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * @author mh.z
 */
@Component
public class CacheSyncStrategyBean implements CacheSyncStrategy, InitSupport {
    private CacheService cacheService;

    @Override
    public void init(RuntimeProvider provider) {
        cacheService = provider.getByType(CacheService.class);
    }

    @Override
    public void sync(String category, String key, Supplier<Object> supplier) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(key, "key null");
        QCheckUtil.notNull(supplier, "supplier null");

        // 目前只是删除这个缓存，若要进一步保证缓存与数据库的数据一致则需要
        // 根据这个设计完善 https://juejin.cn/post/7032578305937276942
        cacheService.remove(category, key);
    }

}
