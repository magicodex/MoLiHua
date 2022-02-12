package jasmine.framework.remote.impl.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QJsonUtil;
import jasmine.core.util.QStringUtil;

import java.io.IOException;

/**
 * @author mh.z
 */
public class ConvertMessageHelper {

    /**
     * 转换类型
     *
     * @param bytes
     * @param type
     * @return
     */
    public Object convertBytes(byte[] bytes, Class<?> type) {
        QCheckUtil.notNull(type, "type");
        Object returnObject = null;

        if (bytes == null || bytes.length == 0) {
            return null;
        }

        if (!type.isPrimitive()) {
            if (bytes.length != 0) {
                if (String.class == type) {
                    returnObject = QStringUtil.utf8Str(bytes);
                } else {
                    ObjectMapper objectMapper = QJsonUtil.getObjectMapper();

                    try {
                        returnObject = objectMapper.readValue(bytes, type);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } else {
            String text = QStringUtil.utf8Str(bytes);
            returnObject = convertText(text, type);
        }

        return returnObject;
    }

    /**
     * 转换类型
     *
     * @param text
     * @param type
     * @return
     */
    public Object convertText(String text, Class<?> type) {
        QCheckUtil.notNull(type, "type");
        Object returnObject = null;

        if (QStringUtil.isEmpty(text)) {
            return null;
        }

        if (Boolean.class == type) {
            //
            returnObject = Boolean.valueOf(text);
        } else if (Character.class == type) {
            //
            char[] charArray = text.toCharArray();
            returnObject = (charArray.length > 0)
                    ? charArray[0] : null;
        } else if (Byte.class == type) {
            //
            returnObject = Byte.valueOf(text);
        } else if (Short.class == type) {
            //
            returnObject = Short.valueOf(text);
        } else if (Integer.class == type) {
            //
            returnObject = Integer.valueOf(text);
        } else if (Long.class == type) {
            //
            returnObject = Long.valueOf(text);
        } else if (Float.class == type) {
            //
            returnObject = Float.valueOf(text);
        } else if (Double.class == type) {
            //
            returnObject = Double.valueOf(text);
        } else {
            throw new IllegalArgumentException("type(" + type.getName() + ") invalid");
        }

        return returnObject;
    }

}
