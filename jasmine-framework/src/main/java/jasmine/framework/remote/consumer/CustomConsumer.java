package jasmine.framework.remote.consumer;

/**
 * 消息消费者
 *
 * @author mh.z
 */
public interface CustomConsumer<T> {

    /**
     * 消费消息
     *
     * @param data
     */
    void consume(T data);

    /**
     * 返回类型
     *
     * @return
     */
    Class<T> getType();
}
