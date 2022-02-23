package jasmine.framework.context;

import jasmine.core.context.RuntimeProvider;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author mh.z
 */
public class SpringRuntimeProvider implements RuntimeProvider, ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringRuntimeProvider.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public <T> T getByType(Class<T> type) {
        return applicationContext.getBean(type);
    }

    @Override
    public <T> T getByType(Class<T> type, boolean required) {
        T bean = null;

        if (required) {
            bean = applicationContext.getBean(type);
        } else {
            try {
                bean = applicationContext.getBean(type);
            } catch (NoSuchBeanDefinitionException e) {
                //
            }
        }

        return bean;
    }

    @Override
    public <T> T getByName(String name) {
        return (T) applicationContext.getBean(name);
    }

    @Override
    public <T> T getByName(String name, boolean required) {
        T bean = null;

        if (required) {
            bean = (T) applicationContext.getBean(name);
        } else {
            try {
                bean = (T) applicationContext.getBean(name);
            } catch (NoSuchBeanDefinitionException e) {
                //
            }
        }

        return bean;
    }

}
