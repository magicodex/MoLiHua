package jasmine.framework.remote.mq.interceptor;

public interface SendInvocation {

    SendInvocationInfo call(String category, Object content);
}
