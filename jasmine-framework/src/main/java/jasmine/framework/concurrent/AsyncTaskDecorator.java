package jasmine.framework.concurrent;

import jasmine.core.context.ContextHandlerFacade;
import jasmine.core.context.ContextSnapshot;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollectionUtil;
import org.springframework.core.task.TaskDecorator;

import java.util.Collection;

/**
 * @author mh.z
 */
public class AsyncTaskDecorator implements TaskDecorator {
    private ContextHandlerFacade handlerFacade;

    public AsyncTaskDecorator(ContextHandlerFacade handlerFacade) {
        this.handlerFacade = handlerFacade;
    }

    @Override
    public Runnable decorate(Runnable runnable) {
        QCheckUtil.notNullProp(handlerFacade, "handlerFacade null");
        Collection<ContextSnapshot> snapshots = handlerFacade.copyAll();

        Runnable proxy = () -> {
            try {
                // 设置上下文
                QCollectionUtil.forEach(snapshots, ContextSnapshot::copyToCurrentThread);

                runnable.run();
            } finally {
                // 清理上下文
                handlerFacade.clearAll();
            }
        };

        return proxy;
    }

}
