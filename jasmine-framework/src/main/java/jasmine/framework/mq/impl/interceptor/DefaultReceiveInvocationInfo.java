package jasmine.framework.mq.impl.interceptor;

import jasmine.framework.mq.interceptor.ReceiveInvocationInfo;

/**
 * @author mh.z
 */
public class DefaultReceiveInvocationInfo implements ReceiveInvocationInfo {
    /** 消息key */
    private String key;
    /** 消息内容 */
    private Object content;
    /** 消息对象 */
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
