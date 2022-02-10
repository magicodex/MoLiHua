package jasmine.framework.remote.consumer;

/**
 * 消息接收接口
 *
 * @author mh.z
 */
public interface ConsumerService {

    /**
     * 接收消息
     *
     * @param category
     * @param data
     */
    void receive(String category, Object data);
}
