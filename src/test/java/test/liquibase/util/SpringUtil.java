package test.liquibase.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

}
