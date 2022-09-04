package jasmine.framework.persistence.datasource.impl;

import jasmine.core.util.function.FunctionWithResult;
import jasmine.core.util.wrapper.ObjectValue;
import jasmine.framework.persistence.datasource.context.DataSourceContext;
import jasmine.framework.persistence.datasource.context.DataSourceContextHolder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author mh.z
 */
public class ReadWriteDataSourceDecideFacadeTest {
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
    public void testReadOny() {
        ObjectValue dataSourceKey = new ObjectValue(null);
        FunctionWithResult action = () -> {
            DataSourceContext context = DataSourceContextHolder.getContext();
            dataSourceKey.set(context.getDataSourceKey());

            return "Hello, test!";
        };

        ReadWriteDataSourceDecideFacade facade = new ReadWriteDataSourceDecideFacade();
        Object actual = facade.readOny(action);

        Assert.assertEquals("Hello, test!", actual);
        Assert.assertEquals("read", dataSourceKey.get());
    }

}
