package jasmine.framework.database.impl.datasource;

import jasmine.framework.database.impl.datasource.MultipleDataSource;
import jasmine.framework.database.datasource.DataSourceContext;
import jasmine.framework.database.datasource.DataSourceContextHolder;
import jasmine.framework.database.impl.datasource.DefaultDataSourceContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

/**
 * @author mh.z
 */
public class MultipleDataSourceTest {
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
    public void testDetermineCurrentLookupKey() {
        MultipleDataSource dataSource = new MultipleDataSource(Collections.emptyMap());

        {
            DataSourceContextHolder.clearContext();
            Object actual = dataSource.determineCurrentLookupKey();

            Assert.assertNull(actual);
        }

        {
            DataSourceContextHolder.setContext(new DefaultDataSourceContext("testDataSource1"));
            Object actual = dataSource.determineCurrentLookupKey();

            Assert.assertEquals("testDataSource1", actual);
        }
    }

}
