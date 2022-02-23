package jasmine.demo.framework.config;

import jasmine.framework.context.SpringRuntimeProvider;
import jasmine.framework.remote.mq.ReceiveMessageService;
import jasmine.framework.remote.mq.SendMessageService;
import jasmine.framework.remote.rabbit.RabbitReceiveMessageService;
import jasmine.framework.remote.rabbit.RabbitSendMessageServiceBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class JasmineFrameworkConfig {
    private SpringRuntimeProvider runtimeProvider;

    public JasmineFrameworkConfig(SpringRuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }

    @Bean
    public ReceiveMessageService receiveMessageService() {
        return new RabbitReceiveMessageService(runtimeProvider);
    }

    @Bean
    public SendMessageService sendMessageService(RabbitTemplate rabbitTemplate) {
        return new RabbitSendMessageServiceBean(runtimeProvider, rabbitTemplate);
    }

}
