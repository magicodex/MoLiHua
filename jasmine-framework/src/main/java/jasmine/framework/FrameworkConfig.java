package jasmine.framework;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class FrameworkConfig {

    /** 是否发布消息到消息队列 */
    @Value("${jasmine.message-queue.publisher.enabled:false}")
    private Boolean messageQueuePublisherEnabled;

    /** 是否消费消息队列的消息 */
    @Value("${jasmine.message-queue.consumer.enabled:false}")
    private Boolean messageQueueConsumerEnabled;

    public Boolean getMessageQueuePublisherEnabled() {
        return messageQueuePublisherEnabled;
    }

    public Boolean getMessageQueueConsumerEnabled() {
        return messageQueueConsumerEnabled;
    }

}
