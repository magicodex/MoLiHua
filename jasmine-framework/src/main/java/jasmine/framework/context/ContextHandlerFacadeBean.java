package jasmine.framework.context;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollectionUtil;
import jasmine.core.util.QNewUtil;
import jasmine.framework.context.handler.ContextHandler;
import jasmine.framework.context.handler.ContextSnapshot;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author mh.z
 */
public class ContextHandlerFacadeBean implements ContextHandlerFacade,
        CustomInitializingSingleton, ApplicationContextAware {
    private List<ContextHandler> handlers;
    private static ApplicationContext applicationContext;

    public ContextHandlerFacadeBean() {
        this.handlers = QNewUtil.list();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ContextHandlerFacadeBean.applicationContext = applicationContext;
    }


    @Override
    public Collection<ContextSnapshot> copyAllFromCurrentThread() {
        QCheckUtil.notNullProp(handlers, "handlers null");

        return QCollectionUtil.forEach(handlers, ContextHandler::copy);
    }

    @Override
    public void initAllToCurrentThread() {
        QCheckUtil.notNullProp(handlers, "handlers null");

        QCollectionUtil.forEach(handlers, ContextHandler::init);
    }

    @Override
    public void clearAllFromCurrentThread() {
        QCheckUtil.notNullProp(handlers, "handlers null");

        QCollectionUtil.forEach(handlers, ContextHandler::clear);
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, ContextHandler> handlerMap = applicationContext
                .getBeansOfType(ContextHandler.class);

        handlers.addAll(handlerMap.values());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
