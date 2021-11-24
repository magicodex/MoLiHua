package test.liquibase.util;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.annotation.TableId;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MybatisPlusUtil {
    private static final Map<Class, Field> ID_FIELD_CACHE;

    static {
        ID_FIELD_CACHE = new ConcurrentHashMap<>();
    }

    public static Field getIdField(Class<?> entityType) {
        Assert.notNull(entityType, "entityType null");

        Field result = ID_FIELD_CACHE.computeIfAbsent(entityType, (mappingKey) -> {
            Field[] fields = ReflectUtil.getFields(mappingKey);

            for (Field field : fields) {
                TableId tableId = field.getAnnotation(TableId.class);

                if (tableId != null) {
                    return field;
                }
            }

            return ReflectUtil.getField(entityType, "id");
        });

        return result;
    }

}
