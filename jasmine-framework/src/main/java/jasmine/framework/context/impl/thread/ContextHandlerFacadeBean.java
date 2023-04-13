package jasmine.framework.context.impl.thread;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.CollectionUtil;
import jasmine.framework.common.util.NewUtil;
import jasmine.framework.context.CustomInitializingSingleton;
import jasmine.framework.context.thread.ContextHandler;
import jasmine.framework.context.thread.ContextHandlerFacade;
import jasmine.framework.context.thread.ContextSnapshot;
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
