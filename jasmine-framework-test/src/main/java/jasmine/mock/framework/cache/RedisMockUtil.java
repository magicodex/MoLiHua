package jasmine.mock.framework.cache;

import jasmine.framework.common.util.NewUtil;

import java.util.Map;

/**
 * @author mh.z
 */
public class RedisMockUtil {
    private static Map<Object, Object> REDIS_MAP;

    static {
        REDIS_MAP = NewUtil.map();
    }

    /**
     * 重置数据
     *
     */
    public static void resetRedis() {
        REDIS_MAP.clear();
    }

    /**
     * 模拟 RedisTemplateInvoker 对象
     *
     * @return
     */
    public static MockRedisTemplateInvoker getRedisTemplateInvoker() {
        return new MockRedisTemplateInvoker(REDIS_MAP);
    }

}
