package jasmine.framework.remote.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QJsonUtil;
import jasmine.core.util.QStringUtil;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author mh.z
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {
    private RuntimeProvider runtimeProvider;

    /** 消息消费者bean的名称后缀 */
    private static final String CONSUMER_BEAN_NAME_SUFFIX = "Consumer";

    public ConsumerServiceImpl(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }

    @Override
    public void consume(String category, Object data) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(data, "data null");

        // 获取消费者
        String consumerName = category + CONSUMER_BEAN_NAME_SUFFIX;
        CustomConsumer consumer = runtimeProvider.getByName(consumerName);
        Class<?> targetType = consumer.getType();

        Message message = (Message) data;
        byte[] messageBody = message.getBody();
        Object targetObject = null;

        if (targetType != null) {
            targetObject = convertBytes(messageBody, targetType);
        } else {
            targetObject = messageBody;
        }

        // 消费消息
        consumer.consume(targetObject);
    }

    /**
     * 转换类型
     *
     * @param bytes
     * @param type
     * @return
     */
    protected Object convertBytes(byte[] bytes, Class<?> type) {
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
    protected Object convertText(String text, Class<?> type) {
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
