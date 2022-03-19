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
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@ConditionalOnClass(RabbitTemplate.class)
@EnableConfigurationProperties(MessageQueueProperties.class)
@Configuration
public class RabbitAutoConfiguration {

    @ConditionalOnMissingBean(ReceiveMessageService.class)
    @Bean
    public ReceiveMessageService receiveMessageService(MessageQueueProperties messageQueueProperties,
                                                       RuntimeProvider runtimeProvider,
                                                       @Autowired(required = false) ReceiveInterceptor interceptor) {
        Boolean receiveEnabled = messageQueueProperties.getConsumer().getEnabled();

        DefaultReceiveMessageService service = new DefaultReceiveMessageService(runtimeProvider);
        service.setEnabled(Boolean.TRUE.equals(receiveEnabled));
        service.setInterceptor(interceptor);

        return service;
    }

    @ConditionalOnMissingBean(SendMessageService.class)
    @Bean
    public SendMessageService sendMessageService(MessageQueueProperties messageQueueProperties,
                                                 RabbitTemplate rabbitTemplate,
                                                 @Autowired(required = false) SendInterceptor interceptor) {
        Boolean sendEnabled = messageQueueProperties.getPublisher().getEnabled();

        DefaultSendMessageServiceBean service = new DefaultSendMessageServiceBean(rabbitTemplate);
        service.setEnabled(Boolean.TRUE.equals(sendEnabled));
        service.setInterceptor(interceptor);

        return service;
    }

}
