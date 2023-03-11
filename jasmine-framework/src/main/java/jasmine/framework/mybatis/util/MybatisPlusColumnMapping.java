package jasmine.framework.mybatis.util;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

/**
 * @author mh.z
 */
public class MybatisPlusColumnMapping extends LinkedHashMap<String, String> {

    public MybatisPlusColumnMapping(Class<?> type) {
        Assert.notNull(type, "type null");
        Field[] fields = ReflectUtil.getFields(type);

        for (Field field : fields) {
            String fieldName = field.getName();
            String columnName = getColumnName(field);

            put(columnName, fieldName);
        }
    }

    /**
     * 返回列名
     *
     * @param field
     * @return
     */
    public String getColumnName(Field field) {
        Assert.notNull(field, "field null");
        String columnName = null;
        TableField tableField = field.getAnnotation(TableField.class);

        if (tableField != null) {
            columnName = tableField.value();

            if (StrUtil.isEmpty(columnName)) {
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
