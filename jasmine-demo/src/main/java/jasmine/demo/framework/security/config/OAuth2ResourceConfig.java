package jasmine.demo.framework.security.config;

import jasmine.framework.util.QStringUtil;
import org.springframework.context.annotation.Configuration;
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

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 只处理请求头中带有 "Authorization" 参数的请求，
        // 其它请求交给 Spring Security 过滤器鉴权。
        http.requestMatcher((request) -> {
            return QStringUtil.isNotEmpty(request.getHeader(AUTHORIZATION_HEADER));
        });

        http.authorizeRequests()
                .anyRequest()
                .authenticated();
    }

}
