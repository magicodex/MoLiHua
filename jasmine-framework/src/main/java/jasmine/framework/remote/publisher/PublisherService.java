package jasmine.framework.remote.publisher;

/**
 * 消息发送接口
 *
 * @author mh.z
 */
public interface PublisherService {

    /**
     * 发送消息
     *
     * @param category
     * @param data
     */
    void send(String category, Object data);
}
