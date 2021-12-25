package jasmine.support.impl.consumer;

import jasmine.common.support.consumer.ConsumerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class Example2ConsumerProvider implements ConsumerProvider {
    private static final Logger logger = LoggerFactory.getLogger(Example2ConsumerProvider.class);

    @Override
    public void consume(Object data) {
        logger.debug("consume <= " + data);
    }

}
