package jasmine.demo.system.remote;

import jasmine.framework.remote.receiver.MessageReceiveProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class Example2MessageReceiveProvider implements MessageReceiveProvider {
    private static final Logger logger = LoggerFactory.getLogger(Example2MessageReceiveProvider.class);

    @Override
    public void receive(Object data) {
        logger.debug("consume <= " + data);
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }

}
