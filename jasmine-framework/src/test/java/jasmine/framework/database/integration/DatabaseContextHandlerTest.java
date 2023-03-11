package jasmine.framework.database.integration;

import jasmine.framework.context.thread.ContextSnapshot;
import jasmine.framework.database.datasource.DataSourceContext;
import jasmine.framework.database.datasource.DataSourceContextHolder;
import jasmine.framework.database.impl.datasource.DefaultDataSourceContext;
import jasmine.framework.database.integration.DatabaseContextHandler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author mh.z
 */
public class DatabaseContextHandlerTest {
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
        DatabaseContextHandler handler = new DatabaseContextHandler();

        DataSourceContext context = new DefaultDataSourceContext("testDataSourceKey");
        DataSourceContextHolder.setContext(context);
        // 复制当前的上下文
        ContextSnapshot snapshot = handler.copy();

        // 先把当前的上下文设置成null
        DataSourceContextHolder.setContext(null);
        Assert.assertNotNull(DataSourceContextHolder.getContext());
        Assert.assertNull(DataSourceContextHolder.getContext().getDataSourceKey());

        // 恢复当前的上下文成设置成null前的上下文
        snapshot.copyToCurrentThread();
        Assert.assertNotNull(DataSourceContextHolder.getContext());
        Assert.assertEquals("testDataSourceKey",
                DataSourceContextHolder.getContext().getDataSourceKey());
    }

}
