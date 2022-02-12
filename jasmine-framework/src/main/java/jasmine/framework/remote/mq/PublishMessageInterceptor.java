package jasmine.framework.remote.mq;

/**
 * <p>
 * 发布消息拦截器。
 * </p>
 *
 * @author mh.z
 */
public interface PublishMessageInterceptor {

    /**
     * 拦截
     *
     * @param provider
     * @param category
     * @param data
     */
    void intercept(PublishMessageProvider provider, String category, Object data);
}
