package jasmine.framework.test.liquibase.loader;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jasmine.framework.test.liquibase.log.TestDataChangeLogMapper;
import jasmine.framework.test.testdependency.Example1;
import jasmine.test.mockito.MockUtil;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author mh.z
 */
public class DefaultTestDataLoaderTest {
    private static final String PATH = "/test/framework/test/liquibase/csv/Example1_1.csv";

    @Test
    public void test() throws IOException {
        ApplicationContext applicationContext = MockUtil.mock(ApplicationContext.class, (target) -> {
            Mockito.when(target.getBean(Mockito.anyString())).then((answer) -> {
                return Mockito.mock(BaseMapper.class);
            });

            Mockito.when(target.getBean(Mockito.any(Class.class))).then((answer) -> {
                return Mockito.mock(TestDataChangeLogMapper.class);
            });
        });

        DefaultTestDataLoader loader = createTestObject();
        loader.init(applicationContext, Example1.class);

        try (InputStream inputStream = getClass().getResourceAsStream(PATH)) {
            // 加载数据
            loader.load(PATH, inputStream);
        }
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
