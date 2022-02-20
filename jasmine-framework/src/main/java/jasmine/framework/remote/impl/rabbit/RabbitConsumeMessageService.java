package jasmine.framework.remote.impl.rabbit;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.FrameworkConfig;
import jasmine.framework.common.conversion.DeserializationHelper;
import jasmine.framework.remote.impl.mq.DefaultConsumeMessageContext;
import jasmine.framework.remote.mq.ConsumeMessageContext;
import jasmine.framework.remote.mq.ConsumeMessageInterceptor;
import jasmine.framework.remote.mq.ConsumeMessageProvider;
import jasmine.framework.remote.mq.ConsumeMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mh.z
 */
@Service
public class RabbitConsumeMessageService implements ConsumeMessageService {
    private static final Logger logger = LoggerFactory.getLogger(RabbitConsumeMessageService.class);
    private RuntimeProvider runtimeProvider;
    /** 配置 */
    private FrameworkConfig config;
    /** 拦截器 */
    private ConsumeMessageInterceptor interceptor;
    /** 反序列化帮助类 */
    private DeserializationHelper deserializationHelper;

    /** 消费提供者名称后缀 */
    private static final String PROVIDER_SUFFIX = "ConsumeMessageProvider";

    public RabbitConsumeMessageService(RuntimeProvider runtimeProvider, FrameworkConfig config,
                                       @Autowired(required = false) ConsumeMessageInterceptor interceptor) {
        this.runtimeProvider = runtimeProvider;
        this.config = config;
        this.interceptor = interceptor;
        this.deserializationHelper = new DeserializationHelper();
    }

    @Override
    public void consume(String category, Object data) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(data, "data null");

        if (Boolean.TRUE.equals(config.getMessageQueuePublisherEnabled())) {
            // 获取消费消息提供者
            ConsumeMessageProvider provider = getProvider(category);
            Class<?> targetType = provider.getType();

            Message message = (Message) data;
            byte[] messageBody = message.getBody();
            Object targetObject = null;
            ConsumeMessageContext context = new DefaultConsumeMessageContext(category);

            if (targetType != null) {
                // 反序列化成对象
                targetObject = deserializationHelper.deserialize(messageBody, targetType);
            } else {
                targetObject = messageBody;
            }

            if (interceptor != null) {
                // 拦截处理
                interceptor.intercept(provider, context, targetObject);
            } else {
                // 消费消息
                provider.consume(context, targetObject);
            }
        } else {
            logger.warn("publish skipped(jasmine.message-queue.consumer.enabled=false)");
        }
    }

    @Override
    public ConsumeMessageProvider getProvider(String category) {
        QCheckUtil.notNull(category, "category null");

        String providerName = category + PROVIDER_SUFFIX;
        ConsumeMessageProvider providerBean = runtimeProvider.getByName(providerName);

        return providerBean;
    }

}
