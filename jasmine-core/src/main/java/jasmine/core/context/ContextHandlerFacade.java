package jasmine.core.context;

import java.util.Collection;
import java.util.List;

/**
 * @author mh.z
 */
public interface ContextHandlerFacade {

    /**
     * 复制上下文
     *
     * @return
     */
    Collection<ContextSnapshot> copyAll();

    /**
     * 初始上下文
     */
    void initAll();

    /**
     * 清除上下文
     */
    void clearAll();
}
