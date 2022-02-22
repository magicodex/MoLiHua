package jasmine.autoconfigure.security.authoriaztion;

import jasmine.core.context.RuntimeProvider;
import jasmine.security.authorization.dynamic.DynamicAccessCheckService;
import jasmine.security.authorization.dynamic.DynamicAccessDecisionManagerProvider;
import jasmine.security.authorization.dynamic.RbacAccessCheckService;
import jasmine.security.authorization.dynamic.DynamicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class AuthorizationAutoConfiguration {

    @Bean
    public RbacAccessCheckService rbacAccessCheckService(RuntimeProvider runtimeProvider) {
        return new RbacAccessCheckService(runtimeProvider);
    }

    @Bean
    public DynamicAccessDecisionManagerProvider dynamicAccessDecisionManagerProvider(DynamicConfig securityConfig,
                                                                                     @Autowired(required = false) DynamicAccessCheckService checkService) {
        return new DynamicAccessDecisionManagerProvider(securityConfig, checkService);
    }

}
