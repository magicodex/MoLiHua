package jasmine.framework.test.liquibase.loader;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jasmine.framework.context.impl.SpringRuntimeProvider;
import jasmine.framework.test.context.AppTestContext;
import jasmine.framework.test.liquibase.log.TestDataChangeLogMapper;
import jasmine.framework.test.testdependency.mapper.Example1Mapper;
import jasmine.framework.test.testdependency.pojo.Example1;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class DefaultTestDataLoaderTest extends AppTestContext {
    @Autowired
    private TestDataChangeLogMapper logMapper;
    @Autowired
    private Example1Mapper exampleMapper;

    private static final String EXAMPLE1_1_PATH = "/test/framework/test/liquibase/csv/Example1_1.csv";

    @Test
    public void test() throws IOException {
        ApplicationContext applicationContext = SpringRuntimeProvider.getApplicationContext();
        DefaultTestDataLoader loader = createTestObject();
        loader.init(applicationContext, Example1.class);

        try (InputStream inputStream = getClass().getResourceAsStream(EXAMPLE1_1_PATH)) {
            loader.load(EXAMPLE1_1_PATH, inputStream);
        }

        // 检查日志记录
        long logTotal = logMapper.selectCount(Wrappers.query());
        Assert.assertEquals(10L, logTotal);

        // 检查数据记录
        long dataTotal = exampleMapper.selectCount(Wrappers.query());
        Assert.assertEquals(10L, dataTotal);
    }

    @Test
    public void testGetMapperBeanName() {
        DefaultTestDataLoader loader = createTestObject();

        String actual = loader.getMapperBeanName(Example1.class);
        Assert.assertEquals("example1Mapper", actual);
    }

    /**
     * 创建测试对象
     *
     * @return
     */
    private static DefaultTestDataLoader createTestObject() {
        DefaultTestDataLoader loader = new DefaultTestDataLoader();

        return loader;
    }

}
