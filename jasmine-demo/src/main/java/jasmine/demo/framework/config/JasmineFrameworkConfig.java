package jasmine.demo.framework.config;

import jasmine.core.context.RuntimeProvider;
import jasmine.demo.framework.mq.CustomRabbitReceiveMessageService;
import jasmine.demo.framework.security.BaseClientDetailsService;
import jasmine.demo.framework.security.CustomRbacCheckService;
import jasmine.demo.framework.security.UserSubjectDetailsService;
import jasmine.framework.JasmineFrameworkConfigTemplate;
import jasmine.framework.remote.mq.DefaultSendMessageService;
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
            return new BaseClientDetailsService(runtimeProvider);
        };
    }

    @Bean
    @Override
    public UserDetailsServiceProvider userDetailsServiceProvider() {
        return () -> {
            return new UserSubjectDetailsService(runtimeProvider);
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
        return new CustomRabbitReceiveMessageService(runtimeProvider);
    }

    @Bean
    @Override
    public SendMessageService sendMessageService() {
        return new DefaultSendMessageService(runtimeProvider);
    }

}
