package jasmine.framework.common.conversion;

import com.fasterxml.jackson.databind.ObjectMapper;
import jasmine.core.util.QJsonUtil;
import jasmine.core.util.QStringUtil;

import java.io.IOException;

/**
 * <p>
 * 序列化帮助类。
 * </p>
 *
 * @author mh.z
 */
public class SerializationHelper {

    /**
     * 序列化
     *
     * @param source
     * @return
     */
    public byte[] serialize(Object source) {
        if (source == null) {
            return null;
        }

        byte[] target = null;
        Class<?> type = source.getClass();

        if (String.class == type) {
            //
            target = QStringUtil.utf8Bytes((String) source);
        } else if (Boolean.class == type
                || Character.class == type
                || Byte.class == type
                || Short.class == type
                || Integer.class == type
                || Long.class == type
                || Float.class == type
                || Double.class == type) {
            //
            String text = QStringUtil.toString(source);
            target = QStringUtil.utf8Bytes(text);
        } else {
            ObjectMapper objectMapper = QJsonUtil.getObjectMapper();

            try {
                target = objectMapper.writeValueAsBytes(source);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return target;
    }

}
