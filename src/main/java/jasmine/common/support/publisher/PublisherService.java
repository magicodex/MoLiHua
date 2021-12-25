package jasmine.common.support.publisher;

/**
 * 消息发布者接口
 *
 * @author mh.z
 */
public interface PublisherService {

    /**
     * 发布消息
     *
     * @param category
     * @param data
     */
    void publish(String category, Object data);
}
