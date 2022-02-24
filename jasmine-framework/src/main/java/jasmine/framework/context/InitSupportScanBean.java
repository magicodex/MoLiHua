package jasmine.framework.context;

import jasmine.core.context.InitSupport;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCollectionUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @author mh.z
 */
public class InitSupportScanBean implements SmartInitializingSingleton, ApplicationContextAware {
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
        if (QCollectionUtil.isNotEmpty(beanMap)) {
            beanMap.forEach((beanName, initSupport) -> {
                initSupport.init(runtimeProvider);
            });
        }
    }

}
