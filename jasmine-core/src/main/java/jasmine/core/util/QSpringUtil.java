package jasmine.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class QSpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        QSpringUtil.applicationContext = applicationContext;
    }

    /**
     * 设置 Spring 上下文
     *
     * @param applicationContext
     */
    public static void setGlobalApplicationContext(ApplicationContext applicationContext) {
        QSpringUtil.applicationContext = applicationContext;
    }

    /**
     * 查找 bean 对象
     *
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 查找 bean 对象
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

}
