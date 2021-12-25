package jasmine.framework.impl.support.publisher;

import jasmine.common.context.RuntimeProvider;
import jasmine.common.support.publisher.PublisherProvider;
import jasmine.common.support.publisher.PublisherService;
import jasmine.common.util.QCheckUtil;
import jasmine.framework.config.FrameworkConfig;
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
            String publisherProviderBeanName = category + "PublisherProvider";
            PublisherProvider publisherProvider = runtimeProvider.getByName(publisherProviderBeanName);

            // 发布消息到消息队列
            publisherProvider.publish(data);
        } else {
            logger.warn("publish skipped(app.message-queue.publisher.enabled=false)");
        }
    }

}
