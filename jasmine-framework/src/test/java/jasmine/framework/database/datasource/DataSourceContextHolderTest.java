package jasmine.framework.database.datasource;

import jasmine.framework.database.datasource.DataSourceContext;
import jasmine.framework.database.datasource.DataSourceContextHolder;
import jasmine.framework.database.impl.datasource.DefaultDataSourceContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author mh.z
 */
public class DataSourceContextHolderTest {
    private DataSourceContext previousContext;

    @Before
    public void setUp() {
        previousContext = DataSourceContextHolder.getContext();
        DataSourceContextHolder.setContext(null);
    }

    @After
    public void tearDown() {
        DataSourceContextHolder.setContext(previousContext);
    }

    @Test
    public void test() {
        DataSourceContext context = new DefaultDataSourceContext();

        // 设置上下文后获取到的上下文就是设置的上下文
        DataSourceContextHolder.setContext(context);
        Assert.assertSame(context, DataSourceContextHolder.getContext());

        // 清空上下文后获取到的上下文是新创建的默认上下文
        DataSourceContextHolder.clearContext();
        Assert.assertNotNull(DataSourceContextHolder.getContext());
        Assert.assertNotSame(context, DataSourceContextHolder.getContext());
    }

}
