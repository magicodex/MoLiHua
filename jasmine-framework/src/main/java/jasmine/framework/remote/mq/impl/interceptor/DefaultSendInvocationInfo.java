package jasmine.framework.remote.mq.impl.interceptor;

import jasmine.framework.remote.mq.interceptor.SendInvocationInfo;

/**
 * @author mh.z
 */
public class DefaultSendInvocationInfo implements SendInvocationInfo {
    /** 消息key */
    private String key;
    /** 消息内容 */
    private Object content;
    /** 消息对象 */
    private Object message;

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

}
