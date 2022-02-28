package jasmine.autoconfigure.framework.middleware;

import jasmine.core.context.RuntimeProvider;
import jasmine.framework.remote.mq.ReceiveMessageService;
import jasmine.framework.remote.mq.SendMessageService;
import jasmine.framework.remote.mq.interceptor.ReceiveInterceptor;
import jasmine.framework.remote.mq.interceptor.SendInterceptor;
import jasmine.framework.remote.rabbit.RabbitReceiveMessageService;
import jasmine.framework.remote.rabbit.RabbitSendMessageServiceBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class RabbitAutoConfiguration {

    @Value("${jasmine.message-queue.consumer.enabled:false}")
    private Boolean receivedEnabled;

    @Value("${jasmine.message-queue.publisher.enabled:false}")
    private Boolean sendEnabled;

    @Bean
    public ReceiveMessageService receiveMessageService(RuntimeProvider runtimeProvider,
                                                       @Autowired(required = false) ReceiveInterceptor interceptor) {
        RabbitReceiveMessageService service = new RabbitReceiveMessageService(runtimeProvider);
        service.setEnabled(Boolean.TRUE.equals(receivedEnabled));
        service.setInterceptor(interceptor);

        return service;
    }

    @Bean
    public SendMessageService sendMessageService(RabbitTemplate rabbitTemplate,
                                                 @Autowired(required = false) SendInterceptor interceptor) {
        RabbitSendMessageServiceBean service = new RabbitSendMessageServiceBean(rabbitTemplate);
        service.setEnabled(Boolean.TRUE.equals(sendEnabled));
        service.setInterceptor(interceptor);

        return service;
    }

}