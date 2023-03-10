package jasmine.framework.persistence.mybatisplus.i18n;

import jasmine.core.util.CollUtil;
import jasmine.framework.common.constant.LangConstants;
import jasmine.framework.persistence.mybatisplus.i18n.support.I18nCrud;
import jasmine.framework.persistence.mybatisplus.i18n.support.I18nMeta;
import jasmine.framework.persistence.mybatisplus.i18n.support.I18nRecord;
import jasmine.framework.persistence.mybatisplus.testdependency.entity.TestEntity1;
import jasmine.framework.persistence.mybatisplus.testdependency.entity.TestEntity2;
import jasmine.framework.persistence.mybatisplus.testdependency.mapper.TestEntity1Mapper;
import jasmine.framework.testdependency.context.FrameworkTestContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class DefaultI18nEntityFacadeTest extends FrameworkTestContext {
    @Autowired
    private TestEntity1Mapper testEntity1Mapper;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    private static final String I18N_TABLE = "test_entity1_i18n";

    @Test
    public void testInsertI18n() {
        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSessionTemplate);
        List<TestEntity1> entityList = new ArrayList<>();
        entityList.add(createEntity(1L, "code1", "name1", "attr1"));
        entityList.add(createEntity(2L, "code2", "name2", "attr2"));

        // 新增多语言信息
        int rowCount = facade.insertI18n(entityList);
        Assert.assertEquals(2, rowCount);

        List<Long> idList = CollUtil.mapToList(entityList, TestEntity1::getId);
        List<I18nRecord> actualList = selectI18n(idList, null);
        // 应当只会查到两条多语言记录
        Assert.assertEquals(2, actualList.size());
    }

    @Test
    public void testUpdateI18n() {
        initI18n(1L);

        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSessionTemplate);
        TestEntity1 entity = new TestEntity1();
        entity.setId(1L);
        entity.setCode1("新代码1");
        entity.setName1("新名称2");

        // 修改多语言信息
        int rowCount = facade.updateI18n(Collections.singletonList(entity));
        Assert.assertEquals(1, rowCount);

        List<I18nRecord> actualList = selectI18n(Collections.singletonList(1L), null);
        Map<String, I18nRecord> actualMap = CollUtil.toMap(actualList, I18nRecord::getLangCode);
        Assert.assertEquals(2L, actualMap.size());

        // 检查中文多语言
        {
            I18nRecord record = actualMap.get(LangConstants.ZH_CN);
            Assert.assertNotNull(record);
            Assert.assertEquals("新名称2", record.getValueAsString("name_1"));
        }

        // 检查英文多语言
        {
            I18nRecord record = actualMap.get(LangConstants.EN_US);
            Assert.assertNotNull(record);
            Assert.assertEquals("name1", record.getValueAsString("name_1"));
        }
    }

    @Test
    public void testPopulateI18n_1() {
        initI18n(1L);

        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSessionTemplate);
        TestEntity1 entity = createEntity(1L, "code1", "name1", "attr1");

        List<TestEntity1> actualList = facade.populateI18n(Collections.singletonList(entity));
        Assert.assertEquals(1, actualList.size());
        Assert.assertEquals("名称1", entity.getName1());
    }

    @Test
    public void testPopulateI18n_2() {
        initI18n(1L);

        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSessionTemplate);
        TestEntity1 entity = createEntity(1L, "code1", "name1", "attr1");

        List<TestEntity1> actualList = facade.populateI18n(Collections.singletonList(entity), TestEntity1.class,
                TestEntity1::getId, (target, source) -> {
                    target.setName1(source.getName1());
                });
        Assert.assertEquals(1, actualList.size());
        Assert.assertEquals("名称1", entity.getName1());
    }

    @Test
    public void testPopulateDefaultI18n() {
        initI18n(1L);

        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSessionTemplate);
        TestEntity1 entity = createEntity(1L, "code1", "name1", "attr1");

        List<TestEntity1> actualList = facade.populateDefaultI18n(Collections.singletonList(entity));
        Assert.assertEquals(1, actualList.size());
        Assert.assertEquals("名称1", entity.getName1());
    }

    @Test
    public void testGetEntityType() {
        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSessionTemplate);

        {
            Class<?> actual = facade.getEntityType(Collections.singletonList(new TestEntity1()));
            Assert.assertEquals(TestEntity1.class, actual);
        }

        {
            Class<?> actual = facade.getEntityType(Collections.emptyList());
            Assert.assertNull(actual);
        }
    }

    @Test
    public void testGetI18nMeta() {
        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSessionTemplate);

        I18nMeta meta = facade.getI18nMeta(TestEntity1.class);
        Assert.assertSame(meta, facade.getI18nMeta(TestEntity1.class));
    }

    @Test
    public void testGetI18nTable() {
        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSessionTemplate);

        Assert.assertEquals("test_entity1_i18n", facade.getI18nTable(TestEntity1.class));
        Assert.assertEquals("test_entity2_i18n", facade.getI18nTable(TestEntity2.class));
    }

    /**
     * 创建 TestEntity1 对象
     *
     * @param id
     * @param code
     * @param name
     * @param attr1
     * @return
     */
    private TestEntity1 createEntity(Long id, String code, String name, String attr1) {
        TestEntity1 entity = new TestEntity1();

        entity.setId(id);
        entity.setCode1(code);
        entity.setName1(name);
        entity.setAttr1(attr1);

        return entity;
    }

    /**
     * 初始多语言数据
     *
     * @param id
     */
    private void initI18n(Long id) {
        I18nCrud crud = new I18nCrud(sqlSessionTemplate, I18N_TABLE);

        {
            Map<String, String> data = new HashMap<>() {{
                put("name_1", "名称1");
            }};

            // 新增多语言记录
            crud.insert(1L, LangConstants.ZH_CN, data, true);
        }

        {
            Map<String, String> data = new HashMap<>() {{
                put("name_1", "name1");
            }};
            // 新增多语言记录
            crud.insert(1L, LangConstants.EN_US, data, false);
        }
    }

    /**
     * 查询多语言数据
     *
     * @param ids
     * @param langCode
     * @return
     */
    private List<I18nRecord> selectI18n(Collection<Long> ids, String langCode) {
        I18nCrud crud = new I18nCrud(sqlSessionTemplate, I18N_TABLE);

        return crud.select(ids, langCode);
    }

}
