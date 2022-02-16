package jasmine.framework.common.conversion;

import com.fasterxml.jackson.databind.ObjectMapper;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QJsonUtil;
import jasmine.core.util.QStringUtil;

import java.io.IOException;

/**
 * <p>
 * 反序列化帮助类。
 * </p>
 *
 * @author mh.z
 */
public class DeserializationHelper {

    /**
     * 反序列化
     *
     * @param source
     * @param type
     * @return
     */
    public <T> T deserialize(byte[] source, Class<T> type) {
        QCheckUtil.notNull(type, "type null");

        if (source == null) {
            return null;
        }

        Object target = null;

        if (String.class == type) {
            //
            target = QStringUtil.utf8Str(source);
        } else if (Boolean.class == type) {
            //
            String text = QStringUtil.utf8Str(source);
            target = Boolean.valueOf(text);
        } else if (Character.class == type) {
            //
            String text = QStringUtil.utf8Str(source);
            char[] charArray = text.toCharArray();
            target = (charArray.length > 0)
                    ? charArray[0] : null;
        } else if (Byte.class == type) {
            //
            String text = QStringUtil.utf8Str(source);
            target = Byte.valueOf(text);
        } else if (Short.class == type) {
            //
            String text = QStringUtil.utf8Str(source);
            target = Short.valueOf(text);
        } else if (Integer.class == type) {
            //
            String text = QStringUtil.utf8Str(source);
            target = Integer.valueOf(text);
        } else if (Long.class == type) {
            //
            String text = QStringUtil.utf8Str(source);
            target = Long.valueOf(text);
        } else if (Float.class == type) {
            //
            String text = QStringUtil.utf8Str(source);
            target = Float.valueOf(text);
        } else if (Double.class == type) {
            //
            String text = QStringUtil.utf8Str(source);
            target = Double.valueOf(text);
        } else {
            ObjectMapper objectMapper = QJsonUtil.getObjectMapper();

            try {
                target = objectMapper.writeValueAsBytes(source);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return (T) target;
    }

}
