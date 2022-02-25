package jasmine.framework.concurrent.context;

/**
 * @author mh.z
 */
public interface ContextCopyHandler {

    /**
     * 复制上下文
     *
     * @return
     */
    ContextSnapshot copy();
}
