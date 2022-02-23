package jasmine.framework.remote.mq.interceptor;

public interface ReceiveInvocation {

    ReceiveInvocationInfo call(String category, Object message);
}
