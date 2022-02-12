package jasmine.framework.remote.mq;

/**
 * <p>
 * 消费消息提供者。
 * </p>
 *
 * @author mh.z
 */
public interface ConsumeMessageProvider {

    /**
     * 消费消息
     *
     * @param data
     */
    void consume(Object data);

    /**
     * 返回类型
     *
     * @return
     */
    Class<?> getType();
}
