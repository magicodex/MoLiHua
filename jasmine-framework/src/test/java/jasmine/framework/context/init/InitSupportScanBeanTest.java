package jasmine.framework.context.init;

import jasmine.framework.common.util.NewUtil;
import jasmine.framework.context.InitSupport;
import jasmine.framework.context.RuntimeProvider;
import jasmine.framework.common.util.ref.ObjectValue;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @author mh.z
 */
public class InitSupportScanBeanTest {

    @Test
    public void test() {
        ObjectValue value = new ObjectValue(null);
        InitSupport initSupport = (runtimeProvider) -> {
            value.set("InitSupport.init called");
        };

        ApplicationContext context = Mockito.mock(ApplicationContext.class);
        Mockito.when(context.getBeansOfType(InitSupport.class))
                .thenReturn(NewUtil.asMap("bean1", initSupport));

        RuntimeProvider provider = Mockito.mock(RuntimeProvider.class);
        InitSupportScanBean scanBean = new InitSupportScanBean(provider);
        scanBean.setApplicationContext(context);

        scanBean.afterSingletonsInstantiated();
        Assert.assertEquals("InitSupport.init called", value.get());
    }

}
