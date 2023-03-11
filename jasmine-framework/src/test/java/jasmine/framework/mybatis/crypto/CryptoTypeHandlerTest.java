package jasmine.framework.mybatis.crypto;

import jasmine.framework.mybatis.testdependency.entity.TestEntity1;
import jasmine.framework.mybatis.testdependency.mapper.TestEntity1Mapper;
import jasmine.framework.testdependency.context.FrameworkTestContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class CryptoTypeHandlerTest extends FrameworkTestContext {
    @Autowired
    private TestEntity1Mapper testEntity1Mapper;

    @Test
    public void test() {
        // 查找记录
        {
            TestEntity1 actual = testEntity1Mapper.selectById(100001L);

            Assert.assertNotNull(actual);
            Assert.assertEquals("Hello, secret!", actual.getSecret1());
        }

        // 保存记录
        {
            TestEntity1 entity = new TestEntity1();
            entity.setId(100002L);
            entity.setCode1("CODE2");
            entity.setName1("NAME2");
            entity.setAttr1("ATTR2");
            entity.setSecret1("Hello, secret!");

            testEntity1Mapper.insert(entity);
            Assert.assertEquals("Hello, secret!", entity.getSecret1());
        }
    }

}
