package jasmine.framework.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import jasmine.core.util.QJsonUtil;
import jasmine.core.util.QStringUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mh.z
 */
public class SimpleConvertUtil {

    /**
     * 序列化
     *
     * @param source
     * @return
     */
    public static byte[] serialize(Object source) {
        if (source == null) {
            return new byte[0];
        }

        ObjectMapper objectMapper = QJsonUtil.getObjectMapper();

        try {
            return objectMapper.writeValueAsBytes(source);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 翻序列化
     *
     * @param source
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] source, Class<T> type) {
        Object object = null;

        if (type == String.class) {
            if (source == null) {
                return null;
            }

            object = QStringUtil.utf8Str(type);
        } else {
            if (source == null || source.length == 0) {
                return null;
            }

            ObjectMapper objectMapper = QJsonUtil.getObjectMapper();

            try {
                object = objectMapper.readValue(source, type);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return (T) object;
    }

    /**
     * 翻序列化成列表
     *
     * @param source
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> deserializeToList(byte[] source, Class<T> type) {
        if (source == null || source.length == 0) {
            return null;
        }

        ObjectMapper objectMapper = QJsonUtil.getObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(ArrayList.class, type);

        // 反序列化成对象
        try {
            List<T> list = objectMapper.readValue(source, collectionType);
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
