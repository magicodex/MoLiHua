package jasmine.framework.remote.mq.interceptor;

public interface ReceiveInvocationInfo {

    String getKey();

    Object getContent();

    Object getMessage();
}
