package jasmine.framework.remote.mq;

/**
 * <p>
 * 接收消息接口。
 * </p>
 *
 * @author mh.z
 */
public interface ReceiveMessageService {

    /**
     * 接收消息
     *
     * @param category
     * @param message
     */
    void receive(String category, Object message);
}
