package jasmine.framework.concurrent;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollectionUtil;
import jasmine.framework.context.handler.ContextHandlerFacade;
import jasmine.framework.context.handler.ContextSnapshot;
import org.springframework.core.task.TaskDecorator;

import java.util.Collection;

/**
 * <p>
 * 异步任务装饰器，初始以及清理线程变量。
 * </p>
 *
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
        Collection<ContextSnapshot> snapshots = handlerFacade.copyAllFromCurrentThread();

        Runnable proxy = () -> {
            try {
                // 设置上下文
                QCollectionUtil.forEach(snapshots, ContextSnapshot::copyToCurrentThread);

                runnable.run();
            } finally {
                // 清理上下文
                handlerFacade.clearAllFromCurrentThread();
            }
        };

        return proxy;
    }

}
