package jasmine.framework.remote.mq.interceptor;

/**
 * @author mh.z
 */
public interface ReceiveInvocationInfo {

    /**
     * 返回 key 值
     *
     * @return
     */
    String getKey();

    /**
     * 返回消息内容
     *
     * @return
     */
    Object getContent();

    /**
     * 返回消息
     *
     * @return
     */
    Object getMessage();

    /**
     * 返回错误
     *
     * @return
     */
    Throwable getError();
}
