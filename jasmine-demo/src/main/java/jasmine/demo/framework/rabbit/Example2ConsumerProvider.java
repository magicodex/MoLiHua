package jasmine.demo.framework.rabbit;

import jasmine.framework.remote.receiver.MessageReceiveProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class Example2ConsumerProvider implements MessageReceiveProvider {
    private static final Logger logger = LoggerFactory.getLogger(Example2ConsumerProvider.class);

    @Override
    public void receive(Object data) {
        logger.debug("consume <= " + data);
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }

}
