package jasmine.framework.test.loader;

import jasmine.framework.test.liquibase.loader.AbstractTestDataLoader;
import jasmine.framework.test.liquibase.log.TestDataChangeLog;
import jasmine.framework.test.liquibase.log.TestDataChangeLogMapper;
import jasmine.test.testdependency.Example1;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * @author mh.z
 */
public class AbstractTestDataLoaderTest {

    @Test
    public void testCreateLog() {
        AbstractTestDataLoader loader = createTestObject();

        Example1 example = new Example1();
        example.setId(1L);
        TestDataChangeLog log = loader.createLog("path.json", example);

        Assert.assertEquals("path.json", log.getResourcePath());
        Assert.assertEquals(Example1.class.getName(), log.getResourceName());
        Assert.assertEquals(Long.valueOf(1L), log.getRecordId());
        Assert.assertNotNull(log.getCreatedDate());
        Assert.assertNotNull(log.getLastUpdatedDate());
    }

    /**
     * 创建测试对象
     *
     * @return
     */
    public static AbstractTestDataLoader createTestObject() {
        // 模拟 TestDataChangeLogMapper 类
        TestDataChangeLogMapper testDataChangeLogMapper = Mockito.mock(TestDataChangeLogMapper.class);
        // 模拟 RuntimeProvider 类
        ApplicationContext applicationContext = Mockito.mock(ApplicationContext.class);
        Mockito.when(applicationContext.getBean(TestDataChangeLogMapper.class)).thenReturn(testDataChangeLogMapper);

        AbstractTestDataLoaderImpl loader = new AbstractTestDataLoaderImpl();
        loader.init(applicationContext, Example1.class);

        return loader;
    }

    /**
     *
     *
     *
     */
    protected static class AbstractTestDataLoaderImpl extends AbstractTestDataLoader {
        @Override
        protected void insertRecords(List<Object> records) {
            //
        }

        @Override
        protected void deleteRecords(List<Long> recordIds) {
            //
        }
    }

}
