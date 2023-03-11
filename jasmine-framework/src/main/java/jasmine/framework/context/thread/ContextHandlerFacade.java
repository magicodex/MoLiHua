package jasmine.framework.context.thread;

import java.util.Collection;

/**
 * <p>
 * 提供初始和清理线程变量的接口。
 * </p>
 *
 * @author mh.z
 */
public interface ContextHandlerFacade {

    /**
     * 复制上下文
     *
     * @return
     */
    Collection<ContextSnapshot> copyAllFromCurrentThread();

    /**
     * 初始上下文
     */
    void initAllToCurrentThread();

    /**
     * 清除上下文
     */
    void clearAllFromCurrentThread();
}
