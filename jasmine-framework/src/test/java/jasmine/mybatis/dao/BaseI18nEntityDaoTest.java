package jasmine.mybatis.dao;

import jasmine.core.i18n.LocaleMessageProvider;
import jasmine.core.util.I18nUtil;
import jasmine.mybatis.i18n.support.I18nMeta;
import jasmine.mybatis.i18n.support.I18nRecord;
import jasmine.mybatis.i18n.support.I18nCrud;
import jasmine.mybatis.testdependency.entity.TestEntity1;
import jasmine.mybatis.testdependency.dao.TestEntity1DAO;
import jasmine.mybatis.testdependency.mapper.TestEntity1Mapper;
import jasmine.framework.testdependency.context.FrameworkTestContext;
import jasmine.mock.core.context.MockLocaleMessageProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class BaseI18nEntityDaoTest extends FrameworkTestContext {
    @Autowired
    private TestEntity1Mapper testEntity1Mapper;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    private LocaleMessageProvider previousLocalMessageProvider;

    @Before
    public void setUp() {
        previousLocalMessageProvider = I18nUtil.getProvider();
    }

    @After
    public void tearDown() {
        I18nUtil.initUtil(previousLocalMessageProvider);
    }

    @Test
    public void testSave() {
        TestEntity1DAO dao = new TestEntity1DAO();
        dao.setBaseMapper(testEntity1Mapper);
        I18nCrud i18nCRUD = new I18nCrud(sqlSessionTemplate, "test_entity1_i18n");

        // 保存记录
        TestEntity1 newEntity = new TestEntity1();
        newEntity.setCode1("code1");
        newEntity.setName1("name1");
        dao.save(newEntity);

        // 查询记录
        TestEntity1 entityPO = dao.getById(newEntity.getId());
        Assert.assertEquals("name1", entityPO.getName1());
        // 查询多语言记录
        List<I18nRecord> i18nRecordList = i18nCRUD.select(
                Collections.singletonList(newEntity.getId()), null);
        Assert.assertEquals(1, i18nRecordList.size());
        // 检查多语言记录
        I18nRecord i18nRecord = i18nRecordList.get(0);
        Assert.assertEquals(I18nUtil.getLanguage(), i18nRecord.getLangCode());
        Assert.assertEquals("name1", i18nRecord.getValueAsString("name_1"));
        Assert.assertEquals(true, i18nRecord.getDefaultFlag());
    }

    @Test
    public void testUpdateById() {
        TestEntity1DAO dao = new TestEntity1DAO();
        dao.setBaseMapper(testEntity1Mapper);
        I18nCrud i18nCRUD = new I18nCrud(sqlSessionTemplate, "test_entity1_i18n");

        // 保存记录
        TestEntity1 newEntity = new TestEntity1();
        newEntity.setCode1("code1");
        newEntity.setName1("name1");
        dao.save(newEntity);

        // 更新记录
        newEntity.setName1("newName1");
        dao.updateById(newEntity);

        // 查询记录
        TestEntity1 entityPO = dao.getById(newEntity.getId());
        Assert.assertEquals("newName1", entityPO.getName1());
        // 查询多语言记录
        List<I18nRecord> i18nRecordList = i18nCRUD.select(
                Collections.singletonList(newEntity.getId()), null);
        Assert.assertEquals(1, i18nRecordList.size());
        // 检查多语言记录
        I18nRecord i18nRecord = i18nRecordList.get(0);
        Assert.assertEquals(I18nUtil.getLanguage(), i18nRecord.getLangCode());
        Assert.assertEquals("newName1", i18nRecord.getValueAsString("name_1"));
        Assert.assertEquals(true, i18nRecord.getDefaultFlag());
    }

    @Test
    public void testDeleteById() {
        TestEntity1DAO dao = new TestEntity1DAO();
        dao.setBaseMapper(testEntity1Mapper);
        I18nCrud i18nCRUD = new I18nCrud(sqlSessionTemplate, "test_entity1_i18n");

        // 保存记录
        TestEntity1 newEntity = new TestEntity1();
        newEntity.setCode1("code1");
        newEntity.setName1("name1");
        dao.save(newEntity);
        // 查询记录
        TestEntity1 entityPO = dao.getById(newEntity.getId());
        Assert.assertNotNull(entityPO);

        // 删除记录
        dao.deleteById(newEntity.getId());

        // 查询记录
        entityPO = dao.getById(newEntity.getId());
        Assert.assertNull(entityPO);
        // 查询多语言记录
        List<I18nRecord> i18nRecordList = i18nCRUD.select(
                Collections.singletonList(newEntity.getId()), null);
        Assert.assertEquals(0, i18nRecordList.size());
    }

    @Test
    public void testGetById() {
        TestEntity1DAO dao = new TestEntity1DAO();
        dao.setBaseMapper(testEntity1Mapper);
        I18nCrud i18nCRUD = new I18nCrud(sqlSessionTemplate, "test_entity1_i18n");
        I18nMeta i18nMeta = I18nMeta.createI18nMeta(TestEntity1.class);

        MockLocaleMessageProvider mockLocaleMessageProvider = new MockLocaleMessageProvider();
        I18nUtil.initUtil(mockLocaleMessageProvider);

        // SIMPLIFIED_CHINESE语言环境下保存记录
        mockLocaleMessageProvider.setLanguage(Locale.SIMPLIFIED_CHINESE.toLanguageTag());
        TestEntity1 newEntity = new TestEntity1();
        newEntity.setCode1("code1");
        newEntity.setName1("chineseName1");
        dao.save(newEntity);
        // 增加ENGLISH语言记录
        newEntity.setName1("englishName1");
        Map<String, String> i18nDataMap = i18nMeta.getI18nData(newEntity);
        i18nCRUD.insert(newEntity.getId(), Locale.ENGLISH.toLanguageTag(), i18nDataMap, false);
        // 查询多语言记录
        List<I18nRecord> i18nRecordList = i18nCRUD.select(
                Collections.singletonList(newEntity.getId()), null);
        Assert.assertEquals(2, i18nRecordList.size());

        // SIMPLIFIED_CHINESE语言环境下查询
        {
            mockLocaleMessageProvider.setLanguage(Locale.SIMPLIFIED_CHINESE.toLanguageTag());

            TestEntity1 entityPO = dao.getById(newEntity.getId());
            Assert.assertEquals("chineseName1", entityPO.getName1());
        }

        // ENGLISH语言环境下查询
        {
            mockLocaleMessageProvider.setLanguage(Locale.ENGLISH.toLanguageTag());

            TestEntity1 entityPO = dao.getById(newEntity.getId());
            Assert.assertEquals("englishName1", entityPO.getName1());
        }
    }

}
