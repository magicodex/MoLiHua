package jasmine.demo.framework.config;

import jasmine.framework.remote.mq.ReceiveMessageService;
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
public class RabbitConsumerConfig {
    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private ReceiveMessageService receiveMessageService;

    @Bean
    public MessageListenerContainer journalSyncConsumer(Queue journalSyncQueue) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(journalSyncQueue);
        container.setConcurrentConsumers(1);

        container.setMessageListener((message) -> {
            receiveMessageService.receive("journalSync", message);
        });

        return container;
    }

}
