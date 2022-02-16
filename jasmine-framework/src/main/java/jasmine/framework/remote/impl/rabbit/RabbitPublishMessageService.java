package jasmine.framework.remote.impl.rabbit;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.FrameworkConfig;
import jasmine.framework.remote.impl.mq.DefaultPublishMessageContext;
import jasmine.framework.remote.mq.PublishMessageContext;
import jasmine.framework.remote.mq.PublishMessageInterceptor;
import jasmine.framework.remote.mq.PublishMessageProvider;
import jasmine.framework.remote.mq.PublishMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mh.z
 */
@Service
public class RabbitPublishMessageService implements PublishMessageService {
    private static final Logger logger = LoggerFactory.getLogger(RabbitPublishMessageService.class);
    private RuntimeProvider runtimeProvider;
    /** 配置 */
    private FrameworkConfig config;
    /** 拦截器 */
    private PublishMessageInterceptor interceptor;

    /** 发布消息提供者名称后缀 */
    private static final String PROVIDER_SUFFIX = "PublishMessageProvider";

    public RabbitPublishMessageService(RuntimeProvider runtimeProvider, FrameworkConfig config,
                                       @Autowired(required = false) PublishMessageInterceptor interceptor) {
        this.runtimeProvider = runtimeProvider;
        this.config = config;
        this.interceptor = interceptor;
    }

    @Override
    public void publish(String category, Object data) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(category, "data null");


        if (Boolean.TRUE.equals(config.getMessageQueuePublisherEnabled())) {
            PublishMessageProvider provider = getProvider(category);

            if (interceptor != null) {
                // 拦截处理
                interceptor.intercept(provider, category, data);
            } else {
                // 发布消息
                PublishMessageContext context = new DefaultPublishMessageContext();
                provider.publish(context, data);
            }
        } else {
            logger.warn("publish skipped(jasmine.message-queue.publisher.enabled=false)");
        }
    }

    @Override
    public PublishMessageProvider getProvider(String category) {
        QCheckUtil.notNull(category, "category null");

        String providerName = category + PROVIDER_SUFFIX;
        PublishMessageProvider providerBean = runtimeProvider.getByName(providerName);

        return providerBean;
    }

}
