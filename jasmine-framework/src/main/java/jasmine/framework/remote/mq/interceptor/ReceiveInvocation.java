package jasmine.framework.remote.mq.interceptor;

public interface ReceiveInvocation {

    ReceiveInvocationInfo invoke(String category, Object message);
}
