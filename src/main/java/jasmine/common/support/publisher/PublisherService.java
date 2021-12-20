package jasmine.common.support.publisher;

/**
 * @author mh.z
 */
public interface PublisherService {

    /**
     * 发布数据
     *
     * @param category
     * @param data
     */
    void publish(String category, Object data);
}
