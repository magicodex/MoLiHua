package jasmine.framework.remote.mq;

/**
 * <p>
 * 发布消息接口。
 * </p>
 *
 * @author mh.z
 */
public interface PublishMessageService {

    /**
     * 发布消息
     *
     * @param category
     * @param data
     */
    void publish(String category, Object data);

    /**
     * 返回提供者
     *
     * @param category
     * @return
     */
    PublishMessageProvider getProvider(String category);
}
