package jasmine.autoconfigure.framework.middleware;

import jasmine.framework.context.SpringRuntimeProvider;
import jasmine.framework.remote.mq.ReceiveMessageService;
import jasmine.framework.remote.mq.SendMessageService;
import jasmine.framework.remote.rabbit.RabbitReceiveMessageService;
import jasmine.framework.remote.rabbit.RabbitSendMessageServiceBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class RabbitAutoConfiguration {
    private SpringRuntimeProvider runtimeProvider;

    @Value("${jasmine.message-queue.consumer.enabled:false}")
    private Boolean receivedEnabled;

    @Value("${jasmine.message-queue.publisher.enabled:false}")
    private Boolean sendEnabled;


    public RabbitAutoConfiguration(SpringRuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }

    @Bean
    public ReceiveMessageService receiveMessageService() {
        RabbitReceiveMessageService service = new RabbitReceiveMessageService(runtimeProvider);
        service.setEnabled(Boolean.TRUE.equals(receivedEnabled));

        return service;
    }

    @Bean
    public SendMessageService sendMessageService(RabbitTemplate rabbitTemplate) {
        RabbitSendMessageServiceBean service = new RabbitSendMessageServiceBean(rabbitTemplate);
        service.setEnabled(Boolean.TRUE.equals(sendEnabled));

        return service;
    }

}
