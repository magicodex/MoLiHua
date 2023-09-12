package jasmine.autoconfigure.security.oauth2;

import jasmine.autoconfigure.security.template.OAuth2AuthorizationConfigsTemplate;
import jasmine.security.config.JasmineSecurityConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * <p>
 * 配置 OAuth2 授权服务器。
 * </p>
 *
 * @author mh.z
 */
@ConditionalOnClass(JasmineSecurityConfig.class)
@EnableAuthorizationServer
@Configuration
public class OAuth2AuthorizationAutoConfiguration extends AuthorizationServerConfigurerAdapter {
    private OAuth2AuthorizationConfigsTemplate configsTemplate;

    public OAuth2AuthorizationAutoConfiguration(OAuth2AuthorizationConfigsTemplate configsTemplate) {
        this.configsTemplate = configsTemplate;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        configsTemplate.configure(security);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        configsTemplate.configure(clients);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        configsTemplate.configure(endpoints);
    }

}
