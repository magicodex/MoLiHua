package jasmine.framework.remote.mq.interceptor;

public class DefaultReceiveInterceptor implements ReceiveInterceptor {

    @Override
    public void onReceive(ReceiveInvocation invocation, String category, Object content) {
        invocation.invoke(category, content);
    }

    @Override
    public void afterDeserialize(ReceiveInvocationInfo callInfo) {
        //
    }

}
