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
     * 拦截
     *
     * @param provider
     * @param context
     * @param data
     */
    void intercept(ConsumeMessageProvider provider, ConsumeMessageContext context, Object data);
}
