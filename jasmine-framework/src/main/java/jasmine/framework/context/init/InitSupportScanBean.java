package jasmine.framework.context.init;

import jasmine.framework.context.CustomInitializingSingleton;
import jasmine.framework.context.InitSupport;
import jasmine.framework.context.RuntimeProvider;
import jasmine.framework.common.util.CollectionUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;

import java.util.Map;

/**
 * <p>
 * 支持 InitSupport 接口，在所有 bean 创建完后
 * 调用实现 InitSupport 接口的类的 init 方法初始。
 * </p>
 *
 * @author mh.z
 */
public class InitSupportScanBean implements CustomInitializingSingleton, ApplicationContextAware {
    private RuntimeProvider runtimeProvider;
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        InitSupportScanBean.applicationContext = applicationContext;
    }

    public InitSupportScanBean(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, InitSupport> beanMap = applicationContext.getBeansOfType(InitSupport.class);

        // 调用初始方法
        if (CollectionUtil.isNotEmpty(beanMap)) {
            beanMap.forEach((beanName, initSupport) -> {
                initSupport.init(runtimeProvider);
            });
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
