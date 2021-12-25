package jasmine.common.support.consumer;

/**
 * 消息消费者接口
 *
 * @author mh.z
 */
public interface ConsumerService {

    /**
     * 消费消息
     *
     * @param category
     * @param data
     */
    void consume(String category, Object data);
}
