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
     * 消费消息
     *
     * @param provider
     * @param category
     * @param data
     */
    void consume(ConsumeMessageProvider provider, String category, Object data);
}
