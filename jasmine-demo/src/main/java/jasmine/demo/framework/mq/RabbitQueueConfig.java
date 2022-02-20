package jasmine.demo.framework.mq;

import jasmine.core.context.RuntimeProvider;
import jasmine.framework.remote.impl.mq.DefaultSendMessageService;
import jasmine.framework.remote.impl.rabbit.RabbitReceiveMessageService;
import jasmine.framework.remote.mq.ReceiveMessageService;
import jasmine.framework.remote.mq.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class RabbitQueueConfig {
    @Autowired
    private RuntimeProvider runtimeProvider;

    @Bean
    public ReceiveMessageService receiveMessageService() {
        return new RabbitReceiveMessageService(runtimeProvider);
    }

    @Bean
    public SendMessageService sendMessageService() {
        return new DefaultSendMessageService(runtimeProvider);
    }

}
