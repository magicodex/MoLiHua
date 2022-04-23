package jasmine.framework.persistence.mybatisplus.i18n;

import jasmine.framework.persistence.mybatisplus.testdependency.entity.TestEntity1;
import jasmine.framework.testdependency.context.FrameworkTestContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class DefaultI18nEntityFacadeTest extends FrameworkTestContext {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void test() {
        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSessionTemplate);

        // 新增多语言信息
        {
            TestEntity1 entity = new TestEntity1();
            entity.setId(1L);
            entity.setCreatedLang("zh-CN");
            entity.setCode1("code1");
            entity.setName1("name1");

            facade.insertI18n(Collections.singletonList(entity));
        }

        // 修改多语言信息
        {
            TestEntity1 entity = new TestEntity1();
            entity.setId(1L);
            entity.setCreatedLang("zh-CN");
            entity.setCode1("code1");
            entity.setName1("NAME1");

            facade.updateI18n(Collections.singletonList(entity));
        }

        // 查询多语言信息
        {
            TestEntity1 entity = new TestEntity1();
            entity.setId(1L);
            entity.setCreatedLang("zh-CN");
            entity.setCode1("code1");
            entity.setName1("");

            facade.populateI18n(Collections.singletonList(entity));
            Assert.assertEquals("code1", entity.getCode1());
            Assert.assertEquals("NAME1", entity.getName1());
        }

        // 删除多语言信息
        {
            facade.deleteI18n(TestEntity1.class, Collections.singletonList(1L));
        }

        // 查询多语言信息
        {
            TestEntity1 entity = new TestEntity1();
            entity.setId(1L);
            entity.setCreatedLang("zh-CN");
            entity.setCode1("code1");
            entity.setName1("");

            facade.populateI18n(Collections.singletonList(entity));
            Assert.assertEquals("code1", entity.getCode1());
            Assert.assertEquals("", entity.getName1());
        }
    }

}
