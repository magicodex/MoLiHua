package jasmine.framework.persistence.mybatisplus.i18n;

import jasmine.framework.persistence.mybatisplus.testdependency.entity.TestEntity1;
import jasmine.framework.testdependency.context.FrameworkTestContext;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class DefaultI18nEntityFacadeTest extends FrameworkTestContext {
    @Autowired
    private SqlSession sqlSession;

    @Test
    public void test() {
        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSession);

        {
            TestEntity1 entity = new TestEntity1();
            entity.setId(1L);
            entity.setLangCode("zh-CN");
            entity.setCode1("code1");
            entity.setName1("name1");

            facade.insertI18n(Collections.singletonList(entity));
        }

        {
            TestEntity1 entity = new TestEntity1();
            entity.setId(1L);
            entity.setLangCode("zh-CN");
            entity.setCode1("code");
            entity.setName1("name");

            facade.populateI18n(Collections.singletonList(entity));
            Assert.assertEquals("name1", entity.getName1());
        }
    }

}
