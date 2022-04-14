package jasmine.framework.persistence.mybatisplus.i18n.support;

import cn.hutool.core.util.ReflectUtil;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.common.util.csv.MybatisPlusColumnMapping;
import jasmine.framework.persistence.annotation.I18n;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 多语言信息。
 * </p>
 *
 * @author mh.z
 */
public class I18nMeta {
    /** 列名与字段的映射集 */
    private Map<String, Field> fields;

    public I18nMeta(Map<String, Field> fields) {
        this.fields = new LinkedHashMap<>(fields);
    }

    protected Map<String, Field> getFields() {
        return fields;
    }

    /**
     * 创建多语言信息
     *
     * @param entityType
     * @return
     */
    public static I18nMeta create(Class<?> entityType) {
        QCheckUtil.notNull(entityType, "entityType null");
        Map<String, Field> fieldMap = new LinkedHashMap<>();
        Field[] fields = ReflectUtil.getFields(entityType);
        MybatisPlusColumnMapping columnMapping = new MybatisPlusColumnMapping(entityType);

        for (Field field : fields) {
            I18n i18n = field.getAnnotation(I18n.class);

            if (i18n != null) {
                String columnName = columnMapping.getColumnName(field);
                fieldMap.put(columnName, field);
            }
        }

        return new I18nMeta(fieldMap);
    }

    /**
     * 返回多语言数据
     *
     * @param entity
     * @return
     */
    public Map<String, String> getI18nData(Object entity) {
        QCheckUtil.notNull(entity, "entity null");
        Map<String, String> i18nDataMap = new LinkedHashMap<>();

        fields.forEach((column, field) -> {
            String value = (String) ReflectUtil.getFieldValue(entity, field);

            i18nDataMap.put(column, value);
        });

        return i18nDataMap;
    }

    /**
     * 填充多语言的值
     *
     * @param entity
     * @param record
     */
    public void populateI18n(Object entity, I18nRecord record) {
        QCheckUtil.notNull(entity, "entity null");

        if (record != null) {
            fields.forEach((column, field) -> {
                Object value = record.getValueAsString(column);

                ReflectUtil.setFieldValue(entity, field, value);
            });
        }
    }

}