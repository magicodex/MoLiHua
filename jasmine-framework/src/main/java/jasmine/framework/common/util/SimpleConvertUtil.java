package jasmine.framework.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import jasmine.core.util.QErrorUtil;
import jasmine.core.util.QJsonUtil;
import jasmine.core.util.QStringUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
    @Nonnull
    public static byte[] serialize(@Nullable Object source) {
        if (source == null) {
            return new byte[0];
        }

        if (source instanceof String) {
            // 转换字符串成字节数组
            return QStringUtil.utf8Bytes((String) source);
        }

        ObjectMapper objectMapper = QJsonUtil.getObjectMapper();
        // 转换成 JSON 字符串
        try {
            return objectMapper.writeValueAsBytes(source);
        } catch (IOException e) {
            throw QErrorUtil.sneakyError(e);
        }
    }

    /**
     * 反序列化
     *
     * @param source
     * @param type
     * @param <T>
     * @return
     */
    @Nullable
    public static <T> T deserialize(@Nullable byte[] source, Class<T> type) {
        Object object = null;

        if (type == String.class) {
            if (source == null) {
                return null;
            }

            // 转换成字符串
            object = QStringUtil.utf8Str(source);
        } else {
            if (source == null || source.length == 0) {
                return null;
            }

            ObjectMapper objectMapper = QJsonUtil.getObjectMapper();
            // 转换成对象
            try {
                object = objectMapper.readValue(source, type);
            } catch (IOException e) {
                throw QErrorUtil.sneakyError(e);
            }
        }

        return (T) object;
    }

    /**
     * 反序列化成列表
     *
     * @param source
     * @param type
     * @param <T>
     * @return
     */
    @Nullable
    public static <T> List<T> deserializeToList(@Nullable byte[] source, Class<T> type) {
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
            throw QErrorUtil.sneakyError(e);
        }
    }

}
