package jasmine.framework.context.thread;

import jasmine.core.util.CheckUtil;
import jasmine.core.util.CollectionUtil;
import jasmine.core.util.NewUtil;
import jasmine.framework.context.CustomInitializingSingleton;
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
        this.handlers = NewUtil.list();
    }

    public List<ContextHandler> getHandlers() {
        return handlers;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ContextHandlerFacadeBean.applicationContext = applicationContext;
    }


    @Override
    public Collection<ContextSnapshot> copyAllFromCurrentThread() {
        CheckUtil.notNullProp(handlers, "handlers null");

        return CollectionUtil.forEach(handlers, ContextHandler::copy);
    }

    @Override
    public void initAllToCurrentThread() {
        CheckUtil.notNullProp(handlers, "handlers null");

        CollectionUtil.forEach(handlers, ContextHandler::init);
    }

    @Override
    public void clearAllFromCurrentThread() {
        CheckUtil.notNullProp(handlers, "handlers null");

        CollectionUtil.forEach(handlers, ContextHandler::clear);
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
