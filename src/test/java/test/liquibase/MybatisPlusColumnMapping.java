package test.liquibase;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import jasmine.common.util.QCheckUtil;
import jasmine.common.util.QStringUtil;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

public class MybatisPlusColumnMapping extends LinkedHashMap<String, String> {

    public MybatisPlusColumnMapping(Class<?> type) {
        QCheckUtil.notNull(type, "type null");
        Field[] fields = ReflectUtil.getFields(type);

        for (Field field : fields) {
            String fieldName = field.getName();
            String columnName = getColumnName(field);

            put(columnName, fieldName);
        }
    }

    protected String getColumnName(Field field) {
        QCheckUtil.notNull(field, "field null");
        String columnName = null;
        TableField tableField = field.getAnnotation(TableField.class);

        if (tableField != null) {
            columnName = tableField.value();

            if (QStringUtil.isEmpty(columnName)) {
                columnName = field.getName();
            }
        } else {
            columnName = field.getName();
            columnName = columnName.replaceAll("[A-Z]", "_$0");
            columnName = columnName.toLowerCase();
        }

        return columnName;
    }

}
