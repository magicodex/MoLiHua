package jasmine.common.support.consumer;

/**
 * @author mh.z
 */
public interface ConsumerProvider {

    /**
     * 消费数据
     *
     * @param data
     */
    void consume(Object data);
}
