package jasmine.framework.context;

import jasmine.core.context.InitSupport;
import jasmine.core.util.QCollectionUtil;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author mh.z
 */
@Component
public class InitSupportScanBean implements SmartInitializingSingleton {
    private SpringRuntimeProvider runtimeProvider;

    public InitSupportScanBean(SpringRuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }

    @Override
    public void afterSingletonsInstantiated() {
        ApplicationContext context = SpringRuntimeProvider.getApplicationContext();
        Map<String, InitSupport> beanMap = context.getBeansOfType(InitSupport.class);

        if (QCollectionUtil.isNotEmpty(beanMap)) {
            beanMap.forEach((beanName, initSupport) -> {
                initSupport.init(runtimeProvider);
            });
        }
    }

}
