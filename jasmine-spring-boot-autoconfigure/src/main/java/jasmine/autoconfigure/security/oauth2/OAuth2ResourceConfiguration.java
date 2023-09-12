package jasmine.autoconfigure.security.oauth2;

import jasmine.autoconfigure.security.template.OAuth2ResourceConfigsTemplate;
import jasmine.framework.common.util.SpringUtil;
import jasmine.framework.common.util.StringUtil;
import jasmine.security.authorization.FilterSecurityInterceptorPostProcessor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @author mh.z
 */
public class OAuth2ResourceConfiguration implements OAuth2ResourceConfigsTemplate, ApplicationContextAware {
    protected ApplicationContext applicationContext;

    private static final String AUTHORIZATION_HEADER = "Authorization";

    public OAuth2ResourceConfiguration() {
        //
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        AccessDecisionManager accessDecisionManager = SpringUtil.getBean(applicationContext,
                AccessDecisionManager.class, true);

        // 只处理请求头中带有 "Authorization" 参数的请求，
        // 其它请求交给 Spring Security 过滤器鉴权。
        http.requestMatcher((request) -> {
            return StringUtil.isNotEmpty(request.getHeader(AUTHORIZATION_HEADER));
        });

        http.authorizeRequests()
                // 没有特别说明的其它请求由访问决策管理器决定能否访问
                .anyRequest()
                .denyAll()
                // 设置访问决策管理器
                .withObjectPostProcessor(new FilterSecurityInterceptorPostProcessor(accessDecisionManager));
    }

}
