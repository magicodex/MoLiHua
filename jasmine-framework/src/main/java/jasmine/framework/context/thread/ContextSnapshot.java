package jasmine.framework.context.thread;

/**
 * <p>
 * 上下文快照。
 * </p>
 *
 * @author mh.z
 */
public interface ContextSnapshot {

    /**
     * 复制上下文到当前线程
     *
     */
    void copyToCurrentThread();
}
