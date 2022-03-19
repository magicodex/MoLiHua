package jasmine.framework.cache;

/**
 * <p>
 * 缓存过期策略。
 * </p>
 *
 * @author mh.z
 */
public interface CacheExpirationStrategy {

    /**
     * 返回过期时间
     *
     * @param category
     * @return
     */
    long getTimeout(String category);
}
