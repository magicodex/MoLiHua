package jasmine.framework.persistence.mybatisplus.i18n.support;

import jasmine.framework.persistence.mybatisplus.testdependency.TestEntity2;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author mh.z
 */
public class I18nMetaTest {

    @Test
    public void testCreate() {
        I18nMeta meta = I18nMeta.create(TestEntity2.class);

        Map<String, Field> fieldMap = meta.getFields();
        Assert.assertEquals(2, fieldMap.size());
        Assert.assertTrue(fieldMap.containsKey("field_2"));
        Assert.assertEquals("field2", fieldMap.get("field_2").getName());
        Assert.assertTrue(fieldMap.containsKey("field_5"));
        Assert.assertEquals("field5", fieldMap.get("field_5").getName());
    }

    @Test
    public void testGetI18nData() {
        TestEntity2 entity = new TestEntity2();
        entity.setField1("value1");
        entity.setField2("value2");
        entity.setField3("value3");
        entity.setField4("value4");
        entity.setField5("value5");

        I18nMeta meta = I18nMeta.create(TestEntity2.class);
        Map<String, String> actualMap = meta.getI18nData(entity);

        Assert.assertEquals(2, actualMap.size());
        Assert.assertEquals("value2", actualMap.get("field_2"));
        Assert.assertEquals("value5", actualMap.get("field_5"));
    }

    @Test
    public void testPopulateI18n() {
        Map<String, Object> valueMap = Map.of("field_2", "value2",
                "field_5", "value5");
        I18nRecord record = new I18nRecord(valueMap);

        I18nMeta meta = I18nMeta.create(TestEntity2.class);
        TestEntity2 entity = new TestEntity2();
        meta.populateI18n(entity, record);

        Assert.assertEquals("value2", entity.getField2());
        Assert.assertEquals("value5", entity.getField5());
    }

}
