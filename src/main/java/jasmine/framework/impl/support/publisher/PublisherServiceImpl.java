package jasmine.framework.impl.support.publisher;

import jasmine.common.context.RuntimeProvider;
import jasmine.common.support.publisher.PublisherProvider;
import jasmine.common.support.publisher.PublisherService;
import jasmine.common.util.QCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author mh.z
 */
@Service
public class PublisherServiceImpl implements PublisherService {
    private static final Logger logger = LoggerFactory.getLogger(PublisherServiceImpl.class);
    private RuntimeProvider runtimeProvider;

    @Value("${app.message-queue.publisher.enabled:false}")
    private Boolean enabled;

    public PublisherServiceImpl(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }

    @Override
    public void publish(String category, Object data) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(data, "data null");

        if (Boolean.TRUE.equals(enabled)) {
            PublisherProvider publisherProvider = runtimeProvider
                    .getByName(category + "PublisherProvider");

            publisherProvider.publish(data);
        } else {
            logger.warn("publish skipped(app.message-queue.publisher.enabled=false)");
        }
    }

}
