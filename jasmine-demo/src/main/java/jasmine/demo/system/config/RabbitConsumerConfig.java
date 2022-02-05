package jasmine.demo.system.config;

import jasmine.framework.remote.receiver.MessageReceiveService;
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
@ConditionalOnProperty(value = "app.message-queue.publisher.enabled",
        havingValue = "true", matchIfMissing = false)
public class RabbitConsumerConfig {
    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private MessageReceiveService receiveService;

    @Bean
    public MessageListenerContainer exampleConsumer1(Queue exampleQueue1) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(exampleQueue1);
        container.setConcurrentConsumers(1);

        container.setMessageListener((message) -> {
            receiveService.receive("example1", message);
        });

        return container;
    }

    @Bean
    public MessageListenerContainer exampleConsumer2(Queue exampleQueue2) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(exampleQueue2);
        container.setConcurrentConsumers(1);

        container.setMessageListener((message) -> {
            receiveService.receive("example2", message);
        });

        return container;
    }

}
