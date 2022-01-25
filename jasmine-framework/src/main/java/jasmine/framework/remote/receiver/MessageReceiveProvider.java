package jasmine.framework.remote.receiver;

/**
 * 消息接收者
 *
 * @author mh.z
 */
public interface MessageReceiveProvider<T> {

    /**
     * 接收消息
     *
     * @param data
     */
    void receive(T data);

    /**
     * 返回类型
     *
     * @return
     */
    Class<T> getType();
}
