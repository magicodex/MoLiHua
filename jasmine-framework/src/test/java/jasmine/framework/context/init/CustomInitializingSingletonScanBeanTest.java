package jasmine.framework.context.init;

import jasmine.core.context.CustomInitializingSingleton;
import jasmine.framework.context.testdependency.MockCustomInitializingSingleton;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author mh.z
 */
public class CustomInitializingSingletonScanBeanTest {
    private MockCustomInitializingSingleton bean1;
    private MockCustomInitializingSingleton bean2;
    private MockCustomInitializingSingleton bean3;

    @Before
    @After
    public void setUpOrTearDown() {
        bean1 = new MockCustomInitializingSingleton(1);
        bean2 = new MockCustomInitializingSingleton(2);
        bean3 = new MockCustomInitializingSingleton(3);
    }

    @Test
    public void test() {
        ApplicationContext context = mockApplicationContext();
        CustomInitializingSingletonScanBean scanBean = new CustomInitializingSingletonScanBean();
        scanBean.setApplicationContext(context);

        scanBean.afterSingletonsInstantiated();
        Assert.assertTrue(bean1.getInstantiatedMethodCalledFlag());
        Assert.assertEquals(1, bean1.getInstantiatedMethodCalledOrder());
        Assert.assertTrue(bean2.getInstantiatedMethodCalledFlag());
        Assert.assertEquals(2, bean2.getInstantiatedMethodCalledOrder());
        Assert.assertTrue(bean3.getInstantiatedMethodCalledFlag());
        Assert.assertEquals(3, bean3.getInstantiatedMethodCalledOrder());
    }

    /**
     * 模拟 ApplicationContext 对象
     *
     * @return
     */
    private ApplicationContext mockApplicationContext() {
        Map<String, CustomInitializingSingleton> beanMap = new LinkedHashMap<>();
        beanMap.put("bean2", bean2);
        beanMap.put("bean3", bean3);
        beanMap.put("bean1", bean1);

        ApplicationContext context = Mockito.mock(ApplicationContext.class);
        Mockito.when(context.getBeansOfType(CustomInitializingSingleton.class)).thenReturn(beanMap);

        return context;
    }

}
