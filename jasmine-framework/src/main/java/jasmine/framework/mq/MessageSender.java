package jasmine.framework.mq;

/**
 * <p>
 * 消息发送者。
 * </p>
 *
 * @author mh.z
 * @param <T>
 */
public interface MessageSender<T> {

    /**
     * 发送消息
     *
     * @param key
     * @param content
     */
    void send(String key, T content);
}
