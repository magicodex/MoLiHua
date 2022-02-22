package jasmine.demo.framework.config;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.context.SubjectProvider;
import jasmine.demo.framework.cache.CustomCacheSyncStrategy;
import jasmine.demo.framework.context.AsyncTaskContextDecorator;
import jasmine.demo.framework.mq.CustomRabbitReceiveMessageService;
import jasmine.demo.framework.security.BaseClientDetailsService;
import jasmine.demo.framework.security.UserSubjectDetailsService;
import jasmine.demo.framework.security.UserSubjectProvider;
import jasmine.framework.JasmineFrameworkConfigTemplate;
import jasmine.framework.cache.CacheSyncStrategy;
import jasmine.framework.remote.mq.DefaultSendMessageService;
import jasmine.framework.remote.mq.ReceiveMessageService;
import jasmine.framework.remote.mq.SendMessageService;
import jasmine.security.JasmineSecurityConfigTemplate;
import jasmine.security.authorization.dynamic.DynamicAccessCheckService;
import jasmine.security.authorization.dynamic.RbacAccessCheckService;
import jasmine.security.subject.ClientDetailsServiceProvider;
import jasmine.security.subject.UserDetailsServiceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;

/**
 * @author mh.z
 */
@Configuration
public class JasmineFrameworkConfig implements JasmineFrameworkConfigTemplate, JasmineSecurityConfigTemplate {
    private RuntimeProvider runtimeProvider;

    public JasmineFrameworkConfig(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }

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
    public DynamicAccessCheckService dynamicAccessCheckService() {
        return new RbacAccessCheckService(runtimeProvider);
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

    @Override
    public CacheSyncStrategy cacheSyncStrategy() {
        return new CustomCacheSyncStrategy();
    }

    @Bean
    @Override
    public TaskDecorator taskDecorator() {
        return new AsyncTaskContextDecorator();
    }

    @Override
    public SubjectProvider subjectProvider() {
        return new UserSubjectProvider();
    }

}
