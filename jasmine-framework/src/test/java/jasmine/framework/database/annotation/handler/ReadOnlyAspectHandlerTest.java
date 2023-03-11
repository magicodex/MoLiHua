package jasmine.framework.database.annotation.handler;

import jasmine.framework.database.annotation.ReadOnly;
import jasmine.framework.database.testdependency.MockDataSourceDecideFacade;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author mh.z
 */
public class ReadOnlyAspectHandlerTest {

    @Test
    public void testAround() throws Throwable {
        ProceedingJoinPoint point = Mockito.mock(ProceedingJoinPoint.class);
        Mockito.when(point.proceed()).thenReturn("Hello, test!");
        ReadOnly readOnly = Mockito.mock(ReadOnly.class);

        // 参数 DataSourceDecideFacade 不为null
        {
            MockDataSourceDecideFacade facade = new MockDataSourceDecideFacade();
            ReadOnlyAspectHandler handler = new ReadOnlyAspectHandler(facade);

            Object actual = handler.around(point, readOnly);
            Assert.assertEquals("Hello, test!", actual);
            Assert.assertNotNull(facade.getLastAction());
        }

        // 参数 DataSourceDecideFacade 为null
        {
            ReadOnlyAspectHandler handler = new ReadOnlyAspectHandler(null);
            Object actual = handler.around(point, readOnly);

            Assert.assertEquals("Hello, test!", actual);
        }
    }


}
