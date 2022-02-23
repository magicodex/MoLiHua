package jasmine.framework.remote.mq.interceptor;

public class DefaultSendInvocationInfo implements SendInvocationInfo {
    private String key;
    private Object content;
    private Object message;
    private Throwable error;

    public DefaultSendInvocationInfo(String key, Object content, Object message) {
        this.key = key;
        this.content = content;
        this.message = message;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Object getContent() {
        return content;
    }

    @Override
    public Object getMessage() {
        return message;
    }

    @Override
    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

}
