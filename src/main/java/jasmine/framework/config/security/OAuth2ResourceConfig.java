package jasmine.framework.config.security;

import jasmine.common.util.QStringUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author mh.z
 */
@EnableResourceServer
@Configuration
public class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatcher((request) -> {
            return QStringUtil.isNotEmpty(request.getHeader("Authorization"));
        });

        http.authorizeRequests()
                .anyRequest()
                .authenticated();
    }

}
