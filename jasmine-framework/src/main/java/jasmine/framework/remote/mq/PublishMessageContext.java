package jasmine.framework.remote.mq;

import java.util.function.Supplier;

/**
 * <p>
 * 发布消息的上下文。
 * </p>
 *
 * @author mh.z
 */
public interface PublishMessageContext {

    /**
     * 返回类别
     *
     * @return
     */
    String getCategory();

    /**
     * 返回标识
     *
     * @param supplier
     * @return
     */
    String computeKey(Supplier<String> supplier);

    /**
     * 返回标识
     *
     * @param supplier
     * @return
     */
    String computeKeyIfAbsent(Supplier<String> supplier);

    /**
     * 返回标识
     *
     * @return
     */
    String getKey();

    /**
     * 设置标识
     *
     * @param key
     */
    void setKey(String key);

    /**
     * 返回属性
     *
     * @param name
     * @return
     */
    Object getAttribute(String name);

    /**
     * 设置属性
     *
     * @param name
     * @param value
     */
    void setAttribute(String name, Object value);
}
