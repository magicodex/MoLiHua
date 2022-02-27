package jasmine.framework.concurrent;

import jasmine.core.context.ContextHandler;
import jasmine.core.context.ContextSnapshot;
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
    private List<ContextHandler> handlers;
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
        List<ContextSnapshot> snapshots = QCollectionUtil.forEach(handlers, ContextHandler::copy);

        Runnable proxy = () -> {
            try {
                // 设置上下文
                QCollectionUtil.forEach(snapshots, ContextSnapshot::copyToCurrentThread);

                runnable.run();
            } finally {
                // 清理上下文
                QCollectionUtil.forEach(handlers, ContextHandler::reset);
            }
        };

        return proxy;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, ContextHandler> handlerMap = applicationContext
                .getBeansOfType(ContextHandler.class);

        handlers.addAll(handlerMap.values());
    }

}
