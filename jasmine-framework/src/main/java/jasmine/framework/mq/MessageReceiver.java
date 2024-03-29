package jasmine.framework.mq;

/**
 * <p>
 * 消息接收者。
 * </p>
 *
 * @author mh.z
 * @param <T>
 */
public interface MessageReceiver<T> {

    /**
     * 返回类型
     *
     * @return
     */
    Class<T> getType();

    /**
     * 返回元素类型
     *
     * @return
     */
    default Class<?> getElementType() {
        return null;
    }

    /**
     * 接收消息
     *
     * @param content
     */
    void receive(T content);
}
