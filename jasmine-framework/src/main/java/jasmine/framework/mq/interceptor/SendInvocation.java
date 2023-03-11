package jasmine.framework.mq.interceptor;

/**
 * @author mh.z
 */
public interface SendInvocation {

    /**
     * 调用
     *
     * @param category
     * @param key
     * @param content
     * @return
     */
    SendInvocationInfo invoke(String category, String key, Object content);
}
