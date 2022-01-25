package jasmine.framework.remote.sender;

/**
 * 消息发送者
 *
 * @author mh.z
 */
public interface MessageSendProvider<T> {

    /**
     * 发送消息
     *
     * @param data
     */
    void send(T data);
}
