package jasmine.framework.context.handler;

/**
 * @author mh.z
 */
public interface ContextHandler {

    /**
     * 复制上下文
     *
     * @return
     */
    ContextSnapshot copy();

    /**
     * 初始上下文
     */
    void init();

    /**
     * 清除上下文
     */
    void clear();
}
