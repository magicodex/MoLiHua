package jasmine.framework.test.liquibase;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jasmine.framework.test.context.AppTestContext;
import jasmine.framework.test.liquibase.log.TestDataChangeLogMapper;
import jasmine.framework.test.testdependency.mapper.Example1Mapper;
import jasmine.framework.test.testdependency.pojo.Example1;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class TestDataTaskChangeTest extends AppTestContext {
    @Autowired
    private TestDataChangeLogMapper logMapper;
    @Autowired
    private Example1Mapper exampleMapper;

    private static final String EXAMPLE1_1_PATH = "/test/framework/test/liquibase/csv/Example1_1.csv";

    @Test
    public void test() throws CustomChangeException {
        TestDataTaskChange testDataTaskChange = new TestDataTaskChange();
        testDataTaskChange.setClassName(Example1.class.getName());
        testDataTaskChange.setResourcePath(EXAMPLE1_1_PATH);
        testDataTaskChange.setFileOpener(new ClassLoaderResourceAccessor());

        // 执行 liquibase 脚本
        testDataTaskChange.execute(Mockito.mock(Database.class));

        // 检查日志记录
        long logTotal = logMapper.selectCount(Wrappers.query());
        Assert.assertEquals(10L, logTotal);

        // 检查数据记录
        long dataTotal = exampleMapper.selectCount(Wrappers.query());
        Assert.assertEquals(10L, dataTotal);
    }

}
