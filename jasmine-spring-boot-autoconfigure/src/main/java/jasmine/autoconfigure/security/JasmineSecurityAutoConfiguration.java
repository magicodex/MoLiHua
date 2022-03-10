package jasmine.autoconfigure.security;

import jasmine.security.authorization.AccessDecisionManagerProxy;
import jasmine.security.authorization.AccessDecisionStrategy;
import jasmine.security.authorization.DynamicAccessDecisionVoter;
import jasmine.security.config.JasmineSecurityConfig;
import jasmine.security.rbac.dao.SecFunctionDao;
import jasmine.security.rbac.dao.SecResourceDao;
import jasmine.security.strategy.DynamicRbacCheckStrategy;
import jasmine.security.subject.UserSubjectDetailsService;
import jasmine.security.support.SecurityContextHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Arrays;

/**
 * @author mh.z
 */
@ConditionalOnClass(JasmineSecurityConfig.class)
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
    public DynamicRbacCheckStrategy rbacCheckService(SecFunctionDao functionDao,
                                                     SecResourceDao resourceDao) {
        return new DynamicRbacCheckStrategy(functionDao, resourceDao);
    }

    @Bean
    public SecurityContextHandler securityContextHandler() {
        return new SecurityContextHandler();
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

    @ConditionalOnMissingBean(UserSubjectDetailsService.class)
    @Bean
    public UserSubjectDetailsService userSubjectDetailsService(JasmineSecurityConfigTemplate configTemplate) {
        UserSubjectDetailsService service = configTemplate.userSubjectDetailsService();

        return service;
    }

    @ConditionalOnMissingBean(ClientDetailsService.class)
    @Bean
    public ClientDetailsService clientDetailsService(JasmineSecurityConfigTemplate configTemplate) {
        ClientDetailsService service = configTemplate.clientDetailsService();

        return service;
    }

}
