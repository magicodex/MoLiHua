package jasmine.autoconfigure.security;

import jasmine.core.context.CurrentSubject;
import jasmine.security.authorization.AccessDecisionManagerProxy;
import jasmine.security.authorization.AccessDecisionStrategy;
import jasmine.security.authorization.DynamicAccessDecisionVoter;
import jasmine.security.config.JasmineSecurityConfig;
import jasmine.security.rbac.dao.SecFunctionDao;
import jasmine.security.rbac.dao.SecResourceDao;
import jasmine.security.strategy.CacheRbacQueryService;
import jasmine.security.strategy.RbacAccessDecisionStrategy;
import jasmine.security.strategy.RbacQueryService;
import jasmine.security.subject.UserSubjectDetailsService;
import jasmine.security.subject.UserSubjectProvider;
import jasmine.security.support.SecurityContextHandler;
import jasmine.security.support.SecurityTenantConfigProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties(JasmineSecurityProperties.class)
@ConditionalOnClass(JasmineSecurityConfig.class)
@Configuration
public class JasmineSecurityAutoConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager(JasmineSecurityProperties securityProperties,
                                                       @Autowired(required = false) AccessDecisionStrategy accessDecisionStrategy) {
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(new OAuth2WebSecurityExpressionHandler());

        // 动态访问决策投票器
        DynamicAccessDecisionVoter dynamicVoter = new DynamicAccessDecisionVoter(accessDecisionStrategy);
        // 访问决策管理器
        AccessDecisionManager manager = new AffirmativeBased(Arrays.asList(webExpressionVoter, dynamicVoter));
        AccessDecisionManagerProxy managerProxy = new AccessDecisionManagerProxy(manager);

        return managerProxy;
    }

    @ConditionalOnProperty(value = "jasmine.security.authorization.strategy",
            havingValue = "rbac", matchIfMissing = false)
    @Bean
    public RbacAccessDecisionStrategy accessDecisionStrategy(SecFunctionDao functionDao,
                                                             SecResourceDao resourceDao) {
        RbacQueryService rbacQueryService = new CacheRbacQueryService(functionDao, resourceDao);
        return new RbacAccessDecisionStrategy(rbacQueryService);
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

    @Bean
    public UserSubjectProvider subjectProvider(UserSubjectDetailsService userSubjectDetailsService) {
        UserSubjectProvider subjectProvider = new UserSubjectProvider(userSubjectDetailsService);

        // 初始工具类
        CurrentSubject.initUtil(subjectProvider);

        return subjectProvider;
    }

    @Bean
    public SecurityContextHandler securityContextHandler() {
        return new SecurityContextHandler();
    }

    @Bean
    public SecurityTenantConfigProcessor securityTenantConfigProcessor() {
        return new SecurityTenantConfigProcessor();
    }

}
