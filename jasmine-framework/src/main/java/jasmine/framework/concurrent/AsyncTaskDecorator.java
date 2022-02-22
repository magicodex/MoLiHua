package jasmine.framework.concurrent;

import jasmine.core.context.InitSupport;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollectionUtil;
import jasmine.framework.context.SpringRuntimeProvider;
import org.springframework.core.task.TaskDecorator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author mh.z
 */
public class AsyncTaskDecorator implements TaskDecorator, InitSupport {
    private List<ContextCopyHandler> handlers;

    public AsyncTaskDecorator() {
        this.handlers = new ArrayList<>();
    }

    @Override
    public void init(RuntimeProvider provider) {
        SpringRuntimeProvider springRuntimeProvider = (SpringRuntimeProvider) provider;
        Map<String, ContextCopyHandler> handlerMap = springRuntimeProvider
                .getMapByType(ContextCopyHandler.class);

        handlers.addAll(handlerMap.values());
    }

    @Override
    public Runnable decorate(Runnable runnable) {
        QCheckUtil.notNullProp(handlers, "handlers null");
        List<ContextSnapshot> snapshots = QCollectionUtil.forEach(handlers, ContextCopyHandler::copy);

        Runnable proxy = () -> {
            try {
                // 设置上下文
                QCollectionUtil.forEach(snapshots, ContextSnapshot::setToCurrentThread);

                runnable.run();
            } finally {
                // 清理上下文
                QCollectionUtil.forEach(snapshots, ContextSnapshot::clearFromCurrentThread);
            }
        };

        return proxy;
    }

}
