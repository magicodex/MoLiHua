package jasmine.common.support.publisher;

/**
 * 消息发布者
 *
 * @author mh.z
 */
public interface PublisherProvider<T> {

    /**
     * 发布消息
     *
     * @param data
     */
    void publish(T data);
}
