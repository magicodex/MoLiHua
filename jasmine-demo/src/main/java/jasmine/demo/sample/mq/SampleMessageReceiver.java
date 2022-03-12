package jasmine.demo.sample.mq;

import jasmine.framework.remote.mq.MessageReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class SampleMessageReceiver implements MessageReceiver<String> {
    private static Logger logger = LoggerFactory.getLogger(SampleMessageReceiver.class);

    @Override
    public Class<String> getType() {
        return String.class;
    }

    @Override
    public void receive(String content) {
        logger.info("receive message(" + content + ").");
    }

}
