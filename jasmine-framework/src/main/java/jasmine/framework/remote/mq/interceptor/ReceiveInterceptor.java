package jasmine.framework.remote.mq.interceptor;

/**
 * @author mh.z
 */
public interface ReceiveInterceptor {

    /**
     * 拦截接收
     *
     * @param invocation
     * @param category
     * @param content
     */
    void onReceive(ReceiveInvocation invocation, String category, Object content);

    /**
     * 转化消息后调用
     *
     * @param invocationInfo
     */
    void afterConvert(ReceiveInvocationInfo invocationInfo);
}
