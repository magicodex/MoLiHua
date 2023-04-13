package jasmine.framework.database.mybatisplus.tenant;

import jasmine.framework.common.util.CollectionUtil;
import jasmine.framework.context.CustomInitializingSingleton;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;

import java.util.Map;

/**
 * <p>
 * 支持 TenantConfigProcessor 接口，在所有 bean 创建完之后，
 * 调用实现该接口的所有 bean 自定义租户配置。
 * </p>
 *
 * @author mh.z
 */
public class TenantConfigProcessorScanBean implements ApplicationContextAware, CustomInitializingSingleton {
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

        if (CollectionUtil.isNotEmpty(beanMap)) {
            beanMap.forEach((beanName, processor) -> {
                processor.ignoreTable(ignoreTableStrategy);
            });
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
