package jasmine.autoconfigure.security.oauth2;

import jasmine.autoconfigure.security.template.OAuth2ResourceConfigsTemplate;
import jasmine.security.config.JasmineSecurityConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
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
@ConditionalOnClass(JasmineSecurityConfig.class)
@EnableResourceServer
@Configuration
public class OAuth2ResourceAutoConfiguration extends ResourceServerConfigurerAdapter {
    private OAuth2ResourceConfigsTemplate configsTemplate;

    public OAuth2ResourceAutoConfiguration(OAuth2ResourceConfigsTemplate configsTemplate) {
        this.configsTemplate = configsTemplate;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        configsTemplate.configure(http);
    }

}
