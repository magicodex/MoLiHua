package jasmine.framework.remote.sender;

import jasmine.framework.common.FrameworkConfig;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author mh.z
 */
@Service
public class MessageSendServiceImpl implements MessageSendService {
    private static final Logger logger = LoggerFactory.getLogger(MessageSendServiceImpl.class);
    private FrameworkConfig frameworkConfig;
    private RuntimeProvider runtimeProvider;

    public MessageSendServiceImpl(FrameworkConfig frameworkConfig,
                                  RuntimeProvider runtimeProvider) {
        this.frameworkConfig = frameworkConfig;
        this.runtimeProvider = runtimeProvider;
    }

    @Override
    public void send(String category, Object data) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(data, "data null");

        if (Boolean.TRUE.equals(frameworkConfig.getMessageQueuePublisherEnabled())) {
            String publisherProviderBeanName = category + "MessageSendProvider";
            MessageSendProvider sendProvider = runtimeProvider.getByName(publisherProviderBeanName);

            // 发布消息到消息队列
            sendProvider.send(data);
        } else {
            logger.warn("publish skipped(app.message-queue.publisher.enabled=false)");
        }
    }

}
