package jasmine.framework.remote.mq;

/**
 * <p>
 * 消费消息接口。
 * </p>
 *
 * @author mh.z
 */
public interface ConsumeMessageService {

    /**
     * 消费消息
     *
     * @param category
     * @param data
     */
    void consume(String category, Object data);

    /**
     * 返回提供者
     *
     * @param category
     * @return
     */
    ConsumeMessageProvider getProvider(String category);
}
