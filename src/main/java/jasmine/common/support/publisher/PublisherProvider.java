package jasmine.common.support.publisher;

/**
 * @author mh.z
 */
public interface PublisherProvider<T> {

    /**
     * 发布数据
     *
     * @param data
     */
    void publish(T data);
}
