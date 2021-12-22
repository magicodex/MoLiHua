package jasmine.framework.impl.context;

import jasmine.common.context.RuntimeProvider;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class SpringRuntimeProvider implements RuntimeProvider, ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringRuntimeProvider.applicationContext = applicationContext;
    }

    @Override
    public <T> T getByType(Class<T> type) {
        return applicationContext.getBean(type);
    }

    @Override
    public <T> T getByName(String name) {
        return (T) applicationContext.getBean(name);
    }

}
