package jasmine.framework.concurrent.impl;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.CollectionUtil;
import jasmine.framework.context.thread.ContextHandlerFacade;
import jasmine.framework.context.thread.ContextSnapshot;
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
        CheckUtil.notNullProp(handlerFacade, "handlerFacade null");
        Collection<ContextSnapshot> snapshots = handlerFacade.copyAllFromCurrentThread();

        Runnable proxy = () -> {
            try {
                // 设置上下文
                CollectionUtil.forEach(snapshots, ContextSnapshot::copyToCurrentThread);

                runnable.run();
            } finally {
                // 清理上下文
                handlerFacade.clearAllFromCurrentThread();
            }
        };

        return proxy;
    }

}
