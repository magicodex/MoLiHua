package jasmine.framework.remote.mq.interceptor;

public class DefaultReceiveInvocationInfo implements ReceiveInvocationInfo {
    private String key;
    private Object content;
    private Object message;

    public DefaultReceiveInvocationInfo(String key, Object content, Object message) {
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

}
