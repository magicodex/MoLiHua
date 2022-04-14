package jasmine.framework.persistence.mybatisplus.dao;

import jasmine.framework.persistence.mybatisplus.testdependency.entity.TestEntity1;
import jasmine.framework.persistence.mybatisplus.testdependency.mapper.TestEntity1Mapper;
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
public class BaseI18nTemplateDaoTest extends FrameworkTestContext {
    @Autowired
    private TestEntity1Mapper testEntity1Mapper;

    @Test
    public void test() {
        BaseI18nTemplateDAO<TestEntity1Mapper, TestEntity1> dao = new BaseI18nTemplateDAO<>();
        dao.setBaseMapper(testEntity1Mapper);

        // 保存记录
        TestEntity1 newEntity = new TestEntity1();
        newEntity.setCode1("code1");
        newEntity.setName1("name1");
        newEntity.setLangCode("zh-CN");
        dao.save(newEntity);

        TestEntity1 entityPO = dao.getById(newEntity.getId());
        Assert.assertEquals("name1", entityPO.getName1());
    }

}
