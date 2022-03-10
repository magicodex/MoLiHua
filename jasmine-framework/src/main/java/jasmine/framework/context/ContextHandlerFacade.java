package jasmine.framework.context;

import jasmine.framework.context.handler.ContextSnapshot;

import java.util.Collection;

/**
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
