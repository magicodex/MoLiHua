package jasmine.framework.remote.mq;

/**
 * <p>
 * 消费消息拦截器。
 * </p>
 *
 * @author mh.z
 */
public interface ConsumeMessageInterceptor {

    /**
     * 拦击
     *
     * @param provider
     * @param category
     * @param data
     */
    void intercept(ConsumeMessageProvider provider, String category, Object data);
}
