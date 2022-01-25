package jasmine.framework.remote.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import jasmine.framework.context.RuntimeProvider;
import jasmine.framework.util.QCheckUtil;
import jasmine.framework.util.QJsonUtil;
import jasmine.framework.util.QStringUtil;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author mh.z
 */
@Service
public class MessageReceiveServiceImpl implements MessageReceiveService {
    private RuntimeProvider runtimeProvider;

    public MessageReceiveServiceImpl(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }

    @Override
    public void receive(String category, Object data) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(data, "data null");

        // 获取消费者
        String consumerProviderBeanName = category + "ConsumerProvider";
        MessageReceiveProvider receiveProvider = runtimeProvider.getByName(consumerProviderBeanName);
        Class<?> targetType = receiveProvider.getType();

        Message message = (Message) data;
        byte[] messageBody = message.getBody();
        Object targetObject = null;

        if (targetType != null) {
            targetObject = convert(messageBody, targetType);
        } else {
            targetObject = messageBody;
        }

        // 消费消息
        receiveProvider.receive(targetObject);
    }

    /**
     * 转换类型
     *
     * @param bytes
     * @param type
     * @return
     */
    protected Object convert(byte[] bytes, Class<?> type) {
        QCheckUtil.notNull(type, "type");

        if (bytes == null) {
            return null;
        }

        if (!type.isPrimitive()) {
            Object returnObject = null;

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

            return returnObject;
        }

        String sourceText = QStringUtil.utf8Str(bytes);
        Object returnObject = null;

        if (Boolean.class == type) {
            //
            returnObject = Boolean.valueOf(sourceText);
        } else if (Character.class == type) {
            //
            char[] charArray = sourceText.toCharArray();
            returnObject = (charArray.length > 0)
                    ? charArray[0] : null;
        } else if (Byte.class == type) {
            //
            returnObject = Byte.valueOf(sourceText);
        } else if (Short.class == type) {
            //
            returnObject = Short.valueOf(sourceText);
        } else if (Integer.class == type) {
            //
            returnObject = Integer.valueOf(sourceText);
        } else if (Long.class == type) {
            //
            returnObject = Long.valueOf(sourceText);
        } else if (Float.class == type) {
            //
            returnObject = Float.valueOf(sourceText);
        } else if (Double.class == type) {
            //
            returnObject = Double.valueOf(sourceText);
        } else {
            throw new IllegalArgumentException("type(" + type.getName() + ") invalid");
        }

        return returnObject;
    }

}
