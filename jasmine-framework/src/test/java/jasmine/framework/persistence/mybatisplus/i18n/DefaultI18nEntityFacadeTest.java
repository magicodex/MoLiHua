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
    public void testInsertI18n() {
        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSessionTemplate);

        // 新增多语言信息
        {
            TestEntity1 entity = new TestEntity1();
            entity.setId(1L);
            entity.setCode1("code1");
            entity.setName1("name1");

            facade.insertI18n(Collections.singletonList(entity));
        }
    }

    @Test
    public void testUpdateI18n() {
        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSessionTemplate);

        // 修改多语言信息
        {
            TestEntity1 entity = new TestEntity1();
            entity.setId(1L);
            entity.setCode1("code1");
            entity.setName1("NAME1");

            facade.updateI18n(Collections.singletonList(entity));
        }
    }

    @Test
    public void testUpdateI18nThenFillEntities() {
        // TODO
    }

    @Test
    public void testDeleteI18n() {
        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSessionTemplate);

        // 删除多语言信息
        {
            facade.deleteI18n(TestEntity1.class, Collections.singletonList(1L));
        }
    }

    @Test
    public void testPopulateI18n() {
        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSessionTemplate);

        // 查询多语言信息
        {
            TestEntity1 entity = new TestEntity1();
            entity.setId(1L);
            entity.setCode1("code1");
            entity.setName1("");

            facade.populateI18n(Collections.singletonList(entity));
            Assert.assertEquals("code1", entity.getCode1());
            Assert.assertEquals("NAME1", entity.getName1());
        }
    }

    @Test
    public void testPopulateDefaultI18n() {
        // TODO
    }

    @Test
    public void testGetEntityType() {
        // TODO
    }

    @Test
    public void testGetI18nMeta() {
        // TODO
    }

    @Test
    public void testGetI18nTable() {
        // TODO
    }

}
