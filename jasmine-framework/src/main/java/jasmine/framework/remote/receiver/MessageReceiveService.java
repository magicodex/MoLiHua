package jasmine.framework.remote.receiver;

/**
 * 消息接收接口
 *
 * @author mh.z
 */
public interface MessageReceiveService {

    /**
     * 接收消息
     *
     * @param category
     * @param data
     */
    void receive(String category, Object data);
}
