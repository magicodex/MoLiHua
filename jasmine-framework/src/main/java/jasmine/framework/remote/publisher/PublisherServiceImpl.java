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
public class PublisherServiceImpl implements PublishService {
    private static final Logger logger = LoggerFactory.getLogger(PublisherServiceImpl.class);
    private FrameworkConfig frameworkConfig;
    private RuntimeProvider runtimeProvider;

    /** 消息发布者bean的名称后缀 */
    private static final String PUBLISHER_BEAN_NAME_SUFFIX = "Publisher";

    public PublisherServiceImpl(FrameworkConfig frameworkConfig,
                                RuntimeProvider runtimeProvider) {
        this.frameworkConfig = frameworkConfig;
        this.runtimeProvider = runtimeProvider;
    }

    @Override
    public void publish(String category, Object data) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(data, "data null");

        if (Boolean.TRUE.equals(frameworkConfig.getMessageQueuePublisherEnabled())) {
            String publisherName = category + PUBLISHER_BEAN_NAME_SUFFIX;
            CustomPublisher publisher = runtimeProvider.getByName(publisherName);

            // 发布消息到消息队列
            publisher.publish(data);
        } else {
            logger.warn("send skipped(app.message-queue.publisher.enabled=false)");
        }
    }

}
