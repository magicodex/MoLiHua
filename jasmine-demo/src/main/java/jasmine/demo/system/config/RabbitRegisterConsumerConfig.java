package jasmine.demo.system.config;

import jasmine.framework.remote.mq.ConsumeMessageService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
@ConditionalOnProperty(value = "jasmine.message-queue.consumer.enabled",
        havingValue = "true", matchIfMissing = false)
public class RabbitRegisterConsumerConfig {
    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private ConsumeMessageService consumeMessageService;

    @Bean
    public MessageListenerContainer exampleConsumer1(Queue exampleQueue1) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(exampleQueue1);
        container.setConcurrentConsumers(1);

        container.setMessageListener((message) -> {
            consumeMessageService.consume("example1", message);
        });

        return container;
    }

    @Bean
    public MessageListenerContainer exampleConsumer2(Queue exampleQueue2) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(exampleQueue2);
        container.setConcurrentConsumers(1);

        container.setMessageListener((message) -> {
            consumeMessageService.consume("example2", message);
        });

        return container;
    }

}
