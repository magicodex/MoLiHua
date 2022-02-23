package jasmine.framework.remote.mq;

/**
 * <p>
 * 发送消息接口。
 * </p>
 *
 * @author mh.z
 */
public interface SendMessageService {

    /**
     * 发送消息
     *
     * @param category
     * @param key
     * @param content
     */
    void send(String category, String key, Object content);

    /**
     * 发送消息
     *
     * @param category
     * @param key
     * @param content
     */
    void sendOnly(String category, String key, Object content);
}
