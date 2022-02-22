package jasmine.security.config;

import jasmine.core.util.QStringUtil;
import jasmine.security.authorization.FilterSecurityInterceptorPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * <p>
 * 配置 OAuth2 资源服务器。
 * </p>
 *
 * @author mh.z
 */
@EnableResourceServer
@Configuration
public class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    /** 访问决策管理器 */
    private AccessDecisionManager accessDecisionManager;

    public OAuth2ResourceConfig(AccessDecisionManager accessDecisionManager) {
        this.accessDecisionManager = accessDecisionManager;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 只处理请求头中带有 "Authorization" 参数的请求，
        // 其它请求交给 Spring Security 过滤器鉴权。
        http.requestMatcher((request) -> {
            return QStringUtil.isNotEmpty(request.getHeader(AUTHORIZATION_HEADER));
        });

        http.authorizeRequests()
                // 没有特别说明的其它请求由访问决策管理器决定能否访问
                .anyRequest()
                .denyAll()
                // 设置访问决策管理器
                .withObjectPostProcessor(new FilterSecurityInterceptorPostProcessor(accessDecisionManager));
    }

}
