package jasmine.framework.remote.publisher;

/**
 * 消息发布者
 *
 * @author mh.z
 */
public interface CustomPublisher<T> {

    /**
     * 发布消息
     *
     * @param data
     */
    void publish(T data);
}
