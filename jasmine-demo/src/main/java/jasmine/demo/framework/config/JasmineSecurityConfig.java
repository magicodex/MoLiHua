package jasmine.demo.framework.config;

import jasmine.core.context.RuntimeProvider;
import jasmine.demo.framework.security.CustomClientDetailsService;
import jasmine.demo.framework.security.CustomRbacCheckService;
import jasmine.demo.framework.security.CustomUserDetailsService;
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
public class JasmineSecurityConfig implements JasmineSecurityConfigTemplate {
    private RuntimeProvider runtimeProvider;
    private SecurityResourceService resourceService;

    public JasmineSecurityConfig(RuntimeProvider runtimeProvider,
                                 SecurityResourceService resourceService) {
        this.runtimeProvider = runtimeProvider;
        this.resourceService = resourceService;
    }

    @Bean
    @Autowired
    public ClientDetailsServiceProvider clientDetailsServiceProvider() {
        return () -> {
            return new CustomClientDetailsService(runtimeProvider);
        };
    }

    @Bean
    @Autowired
    public UserDetailsServiceProvider userDetailsServiceProvider() {
        return () -> {
            return new CustomUserDetailsService(runtimeProvider);
        };
    }

    @Bean
    @Autowired
    public RbacCheckService rbacCheckService() {
        return new CustomRbacCheckService(resourceService);
    }

}
