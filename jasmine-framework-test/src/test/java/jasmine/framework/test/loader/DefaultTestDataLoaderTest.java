package jasmine.framework.test.loader;

import jasmine.framework.test.liquibase.loader.DefaultTestDataLoader;
import jasmine.test.testdependency.Example1;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class DefaultTestDataLoaderTest {

    @Test
    public void test() {
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
