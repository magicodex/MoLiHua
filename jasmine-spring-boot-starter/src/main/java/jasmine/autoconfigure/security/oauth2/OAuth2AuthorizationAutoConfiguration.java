package jasmine.autoconfigure.security.oauth2;

import jasmine.security.subject.ClientDetailsServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;

/**
 * <p>
 * 配置 OAuth2 授权服务器。
 * </p>
 *
 * @author mh.z
 */
@EnableAuthorizationServer
@Configuration
public class OAuth2AuthorizationAutoConfiguration extends AuthorizationServerConfigurerAdapter {
    /** 认证管理器 */
    private final AuthenticationManager authenticationManager;

    private ClientDetailsServiceProvider clientDetailsServiceProvider;

    public OAuth2AuthorizationAutoConfiguration(AuthenticationManager authenticationManager,
                                                @Autowired(required = false) ClientDetailsServiceProvider clientDetailsServiceProvider) {
        this.authenticationManager = authenticationManager;
        this.clientDetailsServiceProvider = clientDetailsServiceProvider;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 开启 /oauth/token_key 验证端口成无权限访问
        security.tokenKeyAccess("permitAll()")
                // 开启 /oauth/check_token 验证端口成认证权限访问
                .checkTokenAccess("isAuthenticated()")
                // 主要是让 /oauth/token 支持 client_id 以及 client_secret 做登录认证
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        if (clientDetailsServiceProvider != null) {
            clients.withClientDetails(clientDetailsServiceProvider.getService());
        } else {
            clients.withClientDetails(new InMemoryClientDetailsService());
        }
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 设置认证管理器
        endpoints.authenticationManager(authenticationManager);
    }

}
