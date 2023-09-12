package jasmine.autoconfigure.security;

import jasmine.autoconfigure.security.template.JasmineSecurityServicesTemplate;
import jasmine.framework.context.CurrentSubject;
import jasmine.framework.context.SubjectProvider;
import jasmine.security.authorization.AccessDecisionManagerProxy;
import jasmine.security.authorization.AccessDecisionStrategy;
import jasmine.security.authorization.DynamicAccessDecisionVoter;
import jasmine.security.config.JasmineSecurityConfig;
import jasmine.security.integration.SecurityContextHandler;
import jasmine.security.integration.SecurityTenantConfigProcessor;
import jasmine.security.rbac.dao.SecFunctionDAO;
import jasmine.security.rbac.dao.SecResourceDAO;
import jasmine.security.strategy.CacheRbacQueryService;
import jasmine.security.strategy.DefaultUrlPatternMatcher;
import jasmine.security.strategy.RbacAccessDecisionStrategy;
import jasmine.security.strategy.RbacQueryService;
import jasmine.security.strategy.UrlPatternMatcher;
import jasmine.security.subject.ClientSubjectDetailsService;
import jasmine.security.subject.UserSubjectDetailsService;
import jasmine.security.subject.UserSubjectProvider;
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

    @ConditionalOnMissingBean(PasswordEncoder.class)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @ConditionalOnMissingBean(AccessDecisionManager.class)
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

    @ConditionalOnMissingBean(UrlPatternMatcher.class)
    @Bean
    public UrlPatternMatcher urlPatternMatcher() {
        return new DefaultUrlPatternMatcher();
    }

    @ConditionalOnProperty(value = "jasmine.security.authorization.strategy",
            havingValue = "rbac", matchIfMissing = false)
    @Bean
    public RbacAccessDecisionStrategy accessDecisionStrategy(SecFunctionDAO functionDAO,
                                                             SecResourceDAO resourceDAO,
                                                             UrlPatternMatcher urlPatternMatcher) {
        RbacQueryService rbacQueryService = new CacheRbacQueryService(functionDAO, resourceDAO);
        return new RbacAccessDecisionStrategy(rbacQueryService, urlPatternMatcher);
    }

    @ConditionalOnMissingBean(UserSubjectDetailsService.class)
    @Bean
    public UserSubjectDetailsService userSubjectDetailsService(JasmineSecurityServicesTemplate servicesTemplate) {
        UserSubjectDetailsService service = servicesTemplate.userSubjectDetailsService();

        return service;
    }

    @ConditionalOnMissingBean(ClientSubjectDetailsService.class)
    @Bean
    public ClientSubjectDetailsService clientSubjectDetailsService(JasmineSecurityServicesTemplate servicesTemplate) {
        ClientSubjectDetailsService service = servicesTemplate.clientSubjectDetailsService();

        return service;
    }

    @ConditionalOnMissingBean(SubjectProvider.class)
    @Bean
    public UserSubjectProvider subjectProvider(UserSubjectDetailsService userSubjectDetailsService) {
        UserSubjectProvider subjectProvider = new UserSubjectProvider(userSubjectDetailsService);

        // 初始工具类
        CurrentSubject.initUtil(subjectProvider);

        return subjectProvider;
    }

    @ConditionalOnMissingBean(SecurityContextHandler.class)
    @Bean
    public SecurityContextHandler securityContextHandler() {
        return new SecurityContextHandler();
    }

    @ConditionalOnMissingBean(SecurityTenantConfigProcessor.class)
    @Bean
    public SecurityTenantConfigProcessor securityTenantConfigProcessor() {
        return new SecurityTenantConfigProcessor();
    }

}
