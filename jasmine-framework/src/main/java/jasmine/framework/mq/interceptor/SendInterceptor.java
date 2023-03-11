package jasmine.framework.mq.interceptor;

/**
 * @author mh.z
 */
public interface SendInterceptor {

    /**
     * 拦截发送
     *
     * @param invocation
     * @param category
     * @param key
     * @param message
     */
    void onSend(SendInvocation invocation, String category, String key, Object message);

    /**
     * 转化消息后调用
     *
     * @param invocationInfo
     */
    void afterConvert(SendInvocationInfo invocationInfo);
}
