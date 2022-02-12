package jasmine.framework.remote.mq;

/**
 * <p>
 * 发布消息提供者。
 * </p>
 *
 * @author mh.z
 */
public interface PublishMessageProvider {

    /**
     * 发布消息
     *
     * @param data
     */
    void publish(Object data);
}
