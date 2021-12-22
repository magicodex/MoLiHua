package jasmine.framework.impl.support.publisher;

import jasmine.common.context.RuntimeProvider;
import jasmine.common.support.publisher.PublisherProvider;
import jasmine.common.support.publisher.PublisherService;
import jasmine.common.util.QCheckUtil;
import org.springframework.stereotype.Service;

/**
 * @author mh.z
 */
@Service
public class PublisherServiceImpl implements PublisherService {
    private RuntimeProvider runtimeProvider;

    public PublisherServiceImpl(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }

    @Override
    public void publish(String category, Object data) {
        QCheckUtil.notNull(category, "category null");
        QCheckUtil.notNull(data, "data null");

        PublisherProvider publisherProvider = runtimeProvider
                .getByName(category + "PublisherProvider");

        publisherProvider.publish(data);
    }

}
