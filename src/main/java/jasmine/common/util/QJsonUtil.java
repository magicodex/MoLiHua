package jasmine.common.util;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * @author mh.z
 */
public class QJsonUtil {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
    }

    /**
     * 序列化对象
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
