package jasmine.framework.remote.publisher;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.FrameworkConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author mh.z
 */
@Service
public class PublisherServiceImpl implements PublisherService {
    private static final Logger logger = LoggerFactory.getLogger(PublisherServiceImpl.class);
    private FrameworkConfig frameworkConfig;
    private RuntimeProvider runtimeProvider;

    /** 消息发送bean的名称后缀 */
    private static final String PROVIDER_BEAN_NAME_SUFFIX = "MessageSendProvider";

    public PublisherServiceImpl(FrameworkConfig frameworkConfig,
                                RuntimeProvider runtimeProvider) {
        this.frameworkConfig = frameworkConfig;
        this.runtimeProvider = runtimeProvider;
    }

    @Override
    public void send(String category, Object data) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(data, "data null");

        if (Boolean.TRUE.equals(frameworkConfig.getMessageQueuePublisherEnabled())) {
            String sendProviderName = category + PROVIDER_BEAN_NAME_SUFFIX;
            PublisherProvider sendProvider = runtimeProvider.getByName(sendProviderName);

            // 发布消息到消息队列
            sendProvider.send(data);
        } else {
            logger.warn("send skipped(app.message-queue.publisher.enabled=false)");
        }
    }

}
