package jasmine.framework.persistence.mybatisplus.tenant;

import jasmine.core.util.QCollectionUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * <p>
 * 支持 TenantConfigProcessor 接口，在所有 bean 创建完之后，
 * 调用实现该接口的所有 bean 自定义租户配置。
 * </p>
 *
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
