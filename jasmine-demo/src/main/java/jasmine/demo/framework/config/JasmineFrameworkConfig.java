package jasmine.demo.framework.config;

import jasmine.core.context.RuntimeProvider;
import jasmine.demo.framework.security.CustomClientDetailsService;
import jasmine.demo.framework.security.CustomRbacCheckService;
import jasmine.demo.framework.security.CustomUserDetailsService;
import jasmine.framework.JasmineFrameworkConfigTemplate;
import jasmine.framework.remote.impl.mq.DefaultSendMessageService;
import jasmine.framework.remote.impl.rabbit.RabbitReceiveMessageService;
import jasmine.framework.remote.mq.ReceiveMessageService;
import jasmine.framework.remote.mq.SendMessageService;
import jasmine.security.JasmineSecurityConfigTemplate;
import jasmine.security.authorization.RbacCheckService;
import jasmine.security.rbac.service.SecurityResourceService;
import jasmine.security.subject.ClientDetailsServiceProvider;
import jasmine.security.subject.UserDetailsServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class JasmineFrameworkConfig implements JasmineFrameworkConfigTemplate, JasmineSecurityConfigTemplate {
    @Autowired
    private RuntimeProvider runtimeProvider;

    @Autowired
    private SecurityResourceService resourceService;

    @Bean
    @Override
    public ClientDetailsServiceProvider clientDetailsServiceProvider() {
        return () -> {
            return new CustomClientDetailsService(runtimeProvider);
        };
    }

    @Bean
    @Override
    public UserDetailsServiceProvider userDetailsServiceProvider() {
        return () -> {
            return new CustomUserDetailsService(runtimeProvider);
        };
    }

    @Bean
    @Override
    public RbacCheckService rbacCheckService() {
        return new CustomRbacCheckService(resourceService);
    }

    @Bean
    @Override
    public ReceiveMessageService receiveMessageService() {
        return new RabbitReceiveMessageService(runtimeProvider);
    }

    @Bean
    @Override
    public SendMessageService sendMessageService() {
        return new DefaultSendMessageService(runtimeProvider);
    }

}
