package jasmine.framework.remote.mq.interceptor;

public interface SendInterceptor {

    void onSend(SendInvocation invocation, String category, Object message);

    void afterSerialize(SendInvocationInfo callInfo);
}
