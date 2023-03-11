package jasmine.framework.mybatis.i18n.support;

import jasmine.framework.mybatis.testdependency.entity.TestEntity2;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author mh.z
 */
public class I18nMetaTest {

    @Test
    public void testCreateI8nMeta() {
        I18nMeta meta = I18nMeta.createI18nMeta(TestEntity2.class);

        Map<String, Field> fieldMap = meta.getFields();
        // TestEntity2 只有 name1 和 name2 加 @I18n 注解
        Assert.assertEquals(2, fieldMap.size());
        Assert.assertTrue(fieldMap.containsKey("name_1"));
        Assert.assertEquals("name1", fieldMap.get("name_1").getName());
        Assert.assertTrue(fieldMap.containsKey("name_2"));
        Assert.assertEquals("name2", fieldMap.get("name_2").getName());
    }

    @Test
    public void testGetI18nData() {
        TestEntity2 entity = new TestEntity2();
        entity.setCode1("code1Value");
        entity.setName1("name1Value");
        entity.setAttr1("attr1Value");
        entity.setCode2("code2Value");
        entity.setName2("name2Value");
        entity.setAttr2("attr2Value");

        I18nMeta meta = I18nMeta.createI18nMeta(TestEntity2.class);
        Map<String, String> actualMap = meta.getI18nData(entity);

        // TestEntity2 只有 name1 和 name2 加 @I18n 注解
        Assert.assertEquals(2, actualMap.size());
        Assert.assertEquals("name1Value", actualMap.get("name_1"));
        Assert.assertEquals("name2Value", actualMap.get("name_2"));
    }

    @Test
    public void testPopulateI18nField() {
        Map<String, Object> valueMap = Map.of("name_1", "name1Value",
                "name_2", "name2Value");
        I18nRecord record = new I18nRecord(valueMap);

        I18nMeta meta = I18nMeta.createI18nMeta(TestEntity2.class);
        TestEntity2 entity = new TestEntity2();
        meta.populateI18nData(entity, record);

        // TestEntity2 只有 name1 和 name2 加 @I18n 注解
        Assert.assertEquals("name1Value", entity.getName1());
        Assert.assertEquals("name2Value", entity.getName2());
    }

}
