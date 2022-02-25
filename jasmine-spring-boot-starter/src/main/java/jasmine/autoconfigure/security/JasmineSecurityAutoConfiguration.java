package jasmine.autoconfigure.security;

import jasmine.security.authorization.AccessDecisionManagerProxy;
import jasmine.security.authorization.AccessDecisionStrategy;
import jasmine.security.authorization.DynamicAccessDecisionVoter;
import jasmine.security.rbac.service.SecFunctionService;
import jasmine.security.rbac.service.SecResourceService;
import jasmine.security.strategy.DynamicRbacCheckStrategy;
import jasmine.security.support.SecurityContextCopyHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Arrays;

@Configuration
public class JasmineSecurityAutoConfiguration {

    /** 是否启用 RBAC 访问控制 */
    @Value("${jasmine.security.rbac.enabled:false}")
    private Boolean rbacEnabled;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DynamicRbacCheckStrategy rbacCheckService(SecFunctionService functionService,
                                                     SecResourceService resourceService) {
        return new DynamicRbacCheckStrategy(functionService, resourceService);
    }

    @Bean
    public SecurityContextCopyHandler securityContextCopyHandler() {
        return new SecurityContextCopyHandler();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager(AccessDecisionStrategy accessDecisionStrategy) {
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(new OAuth2WebSecurityExpressionHandler());

        // 动态访问决策投票器
        DynamicAccessDecisionVoter dynamicVoter = new DynamicAccessDecisionVoter(rbacEnabled, accessDecisionStrategy);
        // 访问决策管理器
        AccessDecisionManager manager = new AffirmativeBased(Arrays.asList(webExpressionVoter, dynamicVoter));
        AccessDecisionManagerProxy managerProxy = new AccessDecisionManagerProxy(manager);

        return managerProxy;
    }

}
