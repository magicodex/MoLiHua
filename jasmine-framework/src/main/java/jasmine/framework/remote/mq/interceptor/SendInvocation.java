package jasmine.framework.remote.mq.interceptor;

public interface SendInvocation {

    SendInvocationInfo invoke(String category, String key, Object content);
}
