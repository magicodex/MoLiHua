package jasmine.demo.config;

import jasmine.framework.remote.mq.ReceiveMessageService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
@ConditionalOnProperty(value = "jasmine.message-queue.consumer.enabled",
        havingValue = "true", matchIfMissing = false)
public class ConsumerConfig {
    private ConnectionFactory connectionFactory;
    private ReceiveMessageService receiveMessageService;

    public ConsumerConfig(ConnectionFactory connectionFactory, ReceiveMessageService receiveMessageService) {
        this.connectionFactory = connectionFactory;
        this.receiveMessageService = receiveMessageService;
    }

    @Bean
    public MessageListenerContainer journalSyncConsumer(Queue journalSyncQueue) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(journalSyncQueue);
        container.setConcurrentConsumers(1);

        container.setMessageListener((message) -> {
            receiveMessageService.receive("sample", message);
        });

        return container;
    }

}
