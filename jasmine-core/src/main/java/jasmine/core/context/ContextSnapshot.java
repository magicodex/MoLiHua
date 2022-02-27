package jasmine.core.context;

/**
 * @author mh.z
 */
public interface ContextSnapshot {

    /**
     * 复制上下文到当前线程
     *
     */
    void copyToCurrentThread();
}
