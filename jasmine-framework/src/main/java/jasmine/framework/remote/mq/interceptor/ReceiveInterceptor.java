package jasmine.framework.remote.mq.interceptor;

public interface ReceiveInterceptor {

    void onReceive(ReceiveInvocation invocation, String category, Object content);

    void afterDeserialize(ReceiveInvocationInfo callInfo);
}
