package jasmine.framework.context.init;

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
public class CustomInitializingSingletonScanBean implements SmartInitializingSingleton, ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CustomInitializingSingletonScanBean.applicationContext = applicationContext;
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
