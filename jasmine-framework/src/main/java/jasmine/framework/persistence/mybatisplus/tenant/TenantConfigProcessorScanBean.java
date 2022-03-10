package jasmine.framework.persistence.mybatisplus.tenant;

import jasmine.core.util.QCollectionUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @author mh.z
 */
public class TenantConfigProcessorScanBean implements ApplicationContextAware, SmartInitializingSingleton {
    private static ApplicationContext applicationContext;
    private IgnoreTableStrategy ignoreTableStrategy;

    public TenantConfigProcessorScanBean(IgnoreTableStrategy ignoreTableStrategy) {
        this.ignoreTableStrategy = ignoreTableStrategy;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        TenantConfigProcessorScanBean.applicationContext = applicationContext;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, TenantConfigProcessor> beanMap = applicationContext
                .getBeansOfType(TenantConfigProcessor.class);

        if (QCollectionUtil.isNotEmpty(beanMap)) {
            beanMap.forEach((beanName, processor) -> {
                processor.ignoreTable(ignoreTableStrategy);
            });
        }
    }

}
