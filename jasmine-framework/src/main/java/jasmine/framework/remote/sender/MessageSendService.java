package jasmine.framework.remote.sender;

/**
 * 消息发送接口
 *
 * @author mh.z
 */
public interface MessageSendService {

    /**
     * 发送消息
     *
     * @param category
     * @param data
     */
    void send(String category, Object data);
}
