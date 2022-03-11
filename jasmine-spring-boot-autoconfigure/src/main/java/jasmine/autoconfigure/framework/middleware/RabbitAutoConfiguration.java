package jasmine.autoconfigure.framework.middleware;

import jasmine.core.context.RuntimeProvider;
import jasmine.framework.remote.mq.ReceiveMessageService;
import jasmine.framework.remote.mq.SendMessageService;
import jasmine.framework.remote.mq.impl.DefaultReceiveMessageService;
import jasmine.framework.remote.mq.impl.DefaultSendMessageServiceBean;
import jasmine.framework.remote.mq.interceptor.ReceiveInterceptor;
import jasmine.framework.remote.mq.interceptor.SendInterceptor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@EnableConfigurationProperties(MessageQueueProperties.class)
@Configuration
public class RabbitAutoConfiguration {

    @Bean
    public ReceiveMessageService receiveMessageService(RuntimeProvider runtimeProvider,
                                                       MessageQueueProperties messageQueueProperties,
                                                       @Autowired(required = false) ReceiveInterceptor interceptor) {
        Boolean receiveEnabled = messageQueueProperties.getConsumer().getEnabled();

        DefaultReceiveMessageService service = new DefaultReceiveMessageService(runtimeProvider);
        service.setEnabled(Boolean.TRUE.equals(receiveEnabled));
        service.setInterceptor(interceptor);

        return service;
    }

    @Bean
    public SendMessageService sendMessageService(RabbitTemplate rabbitTemplate,
                                                 MessageQueueProperties messageQueueProperties,
                                                 @Autowired(required = false) SendInterceptor interceptor) {
        Boolean sendEnabled = messageQueueProperties.getPublisher().getEnabled();

        DefaultSendMessageServiceBean service = new DefaultSendMessageServiceBean(rabbitTemplate);
        service.setEnabled(Boolean.TRUE.equals(sendEnabled));
        service.setInterceptor(interceptor);

        return service;
    }

}
