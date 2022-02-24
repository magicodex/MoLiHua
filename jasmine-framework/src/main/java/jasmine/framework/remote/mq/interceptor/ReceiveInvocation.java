package jasmine.framework.remote.mq.interceptor;

/**
 * @author mh.z
 */
public interface ReceiveInvocation {

    /**
     * 调用
     *
     * @param category
     * @param message
     * @return
     */
    ReceiveInvocationInfo invoke(String category, Object message);
}
