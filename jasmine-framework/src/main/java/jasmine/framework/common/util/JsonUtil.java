package jasmine.framework.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * JSON工具类。
 * </p>
 *
 * @author mh.z
 */
public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    /**
     * 序列化对象成JSON字符串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (IOException e) {
            throw ErrorUtil.sneakyError(e);
        }
    }

    /**
     * 反序列化JSON字符串成对象
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> type) {
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw ErrorUtil.sneakyError(e);
        }
    }

    /**
     * 反序列化JSON字符串成列表
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> fromArray(String json, Class<T> type) {
        TypeFactory typeFactory = OBJECT_MAPPER.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(ArrayList.class, type);

        try {
            List<T> list = OBJECT_MAPPER.readValue(json, collectionType);
            return list;
        } catch (IOException e) {
            throw ErrorUtil.sneakyError(e);
        }
    }

}
