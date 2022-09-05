package jasmine.framework.context;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

/**
 * @author mh.z
 */
public class SpringRuntimeProviderTest {

    @Test
    public void testGetByType() {
        SpringRuntimeProvider provider = new SpringRuntimeProvider();

        ApplicationContext context = Mockito.mock(ApplicationContext.class);
        Mockito.when(context.getBean(Mockito.any(Class.class))).thenThrow(
                new NoSuchBeanDefinitionException("bean1"));
        provider.setApplicationContext(context);

        // 没找到指定类型的对象应当报错的情况
        Assert.assertThrows(Exception.class, () -> {
            provider.getByType(Object.class, true);
        });

        // 没找到指定类型的对象应当不报错的情况
        try {
            Object actual = provider.getByType(Object.class, false);

            Assert.assertNull(actual);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testGetByName() {
        SpringRuntimeProvider provider = new SpringRuntimeProvider();

        ApplicationContext context = Mockito.mock(ApplicationContext.class);
        Mockito.when(context.getBean(Mockito.any(String.class))).thenThrow(
                new NoSuchBeanDefinitionException("bean1"));
        provider.setApplicationContext(context);

        // 没找到指定名称的对象应当报错的情况
        Assert.assertThrows(Exception.class, () -> {
            provider.getByName("bean1", true);
        });

        // 没找到指定名称的对象应当不报错的情况
        try {
            Object actual = provider.getByName("bean1", false);

            Assert.assertNull(actual);
        } catch (Exception e) {
            Assert.fail();
        }
    }

}
