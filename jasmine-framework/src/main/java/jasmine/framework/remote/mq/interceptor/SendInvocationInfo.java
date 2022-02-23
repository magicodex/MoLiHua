package jasmine.framework.remote.mq.interceptor;

public interface SendInvocationInfo {

    String getKey();

    Object getContent();

    Object getMessage();

    Throwable getError();
}
