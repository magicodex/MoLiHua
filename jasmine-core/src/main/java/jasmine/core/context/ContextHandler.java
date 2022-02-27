package jasmine.core.context;

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
}
