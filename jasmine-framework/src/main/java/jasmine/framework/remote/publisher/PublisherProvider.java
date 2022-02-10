package jasmine.framework.remote.publisher;

/**
 * 消息发送者
 *
 * @author mh.z
 */
public interface PublisherProvider<T> {

    /**
     * 发送消息
     *
     * @param data
     */
    void send(T data);
}
