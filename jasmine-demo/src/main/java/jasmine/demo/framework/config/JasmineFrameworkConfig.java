package jasmine.demo.framework.config;

import jasmine.core.context.RuntimeProvider;
import jasmine.framework.JasmineFrameworkConfigTemplate;
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
public class JasmineFrameworkConfig implements JasmineFrameworkConfigTemplate {
    @Autowired
    private RuntimeProvider runtimeProvider;

    @Bean
    @Autowired
    public ReceiveMessageService receiveMessageService() {
        return new RabbitReceiveMessageService(runtimeProvider);
    }

    @Bean
    @Autowired
    public SendMessageService sendMessageService() {
        return new DefaultSendMessageService(runtimeProvider);
    }

}
