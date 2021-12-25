package jasmine.common.support.consumer;

/**
 * 消息消费者
 *
 * @author mh.z
 */
public interface ConsumerProvider {

    /**
     * 消费消息
     *
     * @param data
     */
    void consume(Object data);
}
