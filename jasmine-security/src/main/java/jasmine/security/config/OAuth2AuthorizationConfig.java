package jasmine.security.config;

import jasmine.security.subject.ClientDetailsServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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
@EnableAuthorizationServer
@Configuration
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    /** 认证管理器 */
    private final AuthenticationManager authenticationManager;

    private ClientDetailsServiceProvider clientDetailsServiceProvider;

    public OAuth2AuthorizationConfig(AuthenticationManager authenticationManager,
                                     @Autowired(required = false) ClientDetailsServiceProvider clientDetailsServiceProvider) {
        this.authenticationManager = authenticationManager;
        this.clientDetailsServiceProvider = clientDetailsServiceProvider;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        if (clientDetailsServiceProvider != null) {
            clients.withClientDetails(clientDetailsServiceProvider.getService());
        }
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 设置认证管理器
        endpoints.authenticationManager(authenticationManager);
    }

}
