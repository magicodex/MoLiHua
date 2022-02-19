package jasmine.demo.system.config;

import jasmine.core.context.RuntimeProvider;
import jasmine.demo.system.security.CustomClientDetailsService;
import jasmine.demo.system.security.CustomRbacCheckService;
import jasmine.demo.system.security.CustomUserDetailsService;
import jasmine.security.authorization.RbacCheckService;
import jasmine.security.rbac.service.SecurityResourceService;
import jasmine.security.subject.ClientDetailsServiceProvider;
import jasmine.security.subject.UserDetailsServiceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class SecurityConfig {
    private RuntimeProvider runtimeProvider;
    private SecurityResourceService resourceService;

    public SecurityConfig(RuntimeProvider runtimeProvider,
                          SecurityResourceService resourceService) {
        this.runtimeProvider = runtimeProvider;
        this.resourceService = resourceService;
    }

    @Bean
    public ClientDetailsServiceProvider clientDetailsServiceProvider() {
        return () -> {
            return new CustomClientDetailsService(runtimeProvider);
        };
    }

    @Bean
    public UserDetailsServiceProvider userDetailsServiceProvider() {
        return () -> {
            return new CustomUserDetailsService(runtimeProvider);
        };
    }

    @Bean
    public RbacCheckService rbacCheckService() {
        return new CustomRbacCheckService(resourceService);
    }

}
