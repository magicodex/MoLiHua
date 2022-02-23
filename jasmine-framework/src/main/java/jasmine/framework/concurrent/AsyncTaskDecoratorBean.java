package jasmine.framework.concurrent;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollectionUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.task.TaskDecorator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author mh.z
 */
public class AsyncTaskDecoratorBean implements TaskDecorator, SmartInitializingSingleton, ApplicationContextAware {
    private List<ContextCopyHandler> handlers;
    private static ApplicationContext applicationContext;

    public AsyncTaskDecoratorBean() {
        this.handlers = new ArrayList<>();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AsyncTaskDecoratorBean.applicationContext = applicationContext;
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

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, ContextCopyHandler> handlerMap = applicationContext
                .getBeansOfType(ContextCopyHandler.class);

        handlers.addAll(handlerMap.values());
    }

}
