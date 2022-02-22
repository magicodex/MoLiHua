package jasmine.framework.concurrent;

/**
 * @author mh.z
 */
public interface ContextSnapshot {

    /**
     * 设置上下文到当前线程
     *
     */
    void setToCurrentThread();

    /**
     * 从当前线程清除上下文
     *
     */
    void clearFromCurrentThread();
}
