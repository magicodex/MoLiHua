package jasmine.common.support.consumer;

/**
 * @author mh.z
 */
public interface ConsumerService {

    /**
     * 消费数据
     *
     * @param category
     * @param data
     */
    void consume(String category, Object data);
}
