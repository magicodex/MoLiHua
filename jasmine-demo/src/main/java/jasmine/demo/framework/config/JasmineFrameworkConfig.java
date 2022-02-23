package jasmine.demo.framework.config;

import jasmine.demo.framework.security.BaseClientDetailsService;
import jasmine.demo.framework.security.UserSubjectDetailsServiceImpl;
import jasmine.framework.context.SpringRuntimeProvider;
import jasmine.framework.remote.mq.ReceiveMessageService;
import jasmine.framework.remote.mq.SendMessageService;
import jasmine.framework.remote.rabbit.RabbitReceiveMessageService;
import jasmine.framework.remote.rabbit.RabbitSendMessageService;
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
    public BaseClientDetailsService baseClientDetailsService() {
        return new BaseClientDetailsService(runtimeProvider);
    }

    @Bean
    public UserSubjectDetailsServiceImpl userSubjectDetailsServiceImpl() {
        return new UserSubjectDetailsServiceImpl(runtimeProvider);
    }

    @Bean
    public ReceiveMessageService receiveMessageService() {
        return new RabbitReceiveMessageService(runtimeProvider);
    }

    @Bean
    public SendMessageService sendMessageService() {
        return new RabbitSendMessageService(runtimeProvider);
    }

}
