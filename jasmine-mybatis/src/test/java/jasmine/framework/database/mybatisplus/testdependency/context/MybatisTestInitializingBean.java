package jasmine.framework.database.mybatisplus.testdependency.context;

import jasmine.core.context.CustomInitializingSingleton;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author mh.z
 */
public class MybatisTestInitializingBean implements SmartInitializingSingleton, ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MybatisTestInitializingBean.applicationContext = applicationContext;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, CustomInitializingSingleton> beanMap = applicationContext
                .getBeansOfType(CustomInitializingSingleton.class);

        List<CustomInitializingSingleton> beanList = new ArrayList<>(beanMap.values());
        beanList.sort(Comparator.comparing(CustomInitializingSingleton::getOrder));

        beanList.forEach(CustomInitializingSingleton::afterSingletonsInstantiated);
    }

}
