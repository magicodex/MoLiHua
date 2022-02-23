package jasmine.autoconfigure.security;

import jasmine.autoconfigure.security.rbac.RbacProperties;
import jasmine.core.context.RuntimeProvider;
import jasmine.security.authorization.AccessDecisionManagerProxy;
import jasmine.security.authorization.dynamic.DynamicAccessDecisionVoter;
import jasmine.security.authorization.rbac.RbacAccessCheckService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RbacProperties rbacProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RbacAccessCheckService rbacAccessCheckService(RuntimeProvider runtimeProvider) {
        return new RbacAccessCheckService(runtimeProvider);
    }

    @Bean
    public AccessDecisionManager accessDecisionManager(RbacAccessCheckService checkService) {
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(new OAuth2WebSecurityExpressionHandler());

        // 动态访问决策投票器
        DynamicAccessDecisionVoter dynamicVoter = new DynamicAccessDecisionVoter(rbacProperties.getRbacEnabled(), checkService);
        // 访问决策管理器
        AccessDecisionManager manager = new AffirmativeBased(Arrays.asList(webExpressionVoter, dynamicVoter));
        AccessDecisionManagerProxy managerProxy = new AccessDecisionManagerProxy(manager);

        return managerProxy;
    }

}
