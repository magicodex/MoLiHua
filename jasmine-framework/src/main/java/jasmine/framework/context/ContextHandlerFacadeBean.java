package jasmine.framework.context;

import jasmine.core.context.handler.ContextHandler;
import jasmine.core.context.handler.ContextHandlerFacade;
import jasmine.core.context.handler.ContextSnapshot;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollectionUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author mh.z
 */
public class ContextHandlerFacadeBean implements ContextHandlerFacade, SmartInitializingSingleton, ApplicationContextAware {
    private List<ContextHandler> handlers;
    private static ApplicationContext applicationContext;

    public ContextHandlerFacadeBean() {
        this.handlers = new ArrayList<>();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ContextHandlerFacadeBean.applicationContext = applicationContext;
    }


    @Override
    public Collection<ContextSnapshot> copyAll() {
        QCheckUtil.notNullProp(handlers, "handlers null");

        return QCollectionUtil.forEach(handlers, ContextHandler::copy);
    }

    @Override
    public void initAll() {
        QCheckUtil.notNullProp(handlers, "handlers null");

        QCollectionUtil.forEach(handlers, ContextHandler::init);
    }

    @Override
    public void clearAll() {
        QCheckUtil.notNullProp(handlers, "handlers null");

        QCollectionUtil.forEach(handlers, ContextHandler::clear);
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, ContextHandler> handlerMap = applicationContext
                .getBeansOfType(ContextHandler.class);

        handlers.addAll(handlerMap.values());
    }

}
