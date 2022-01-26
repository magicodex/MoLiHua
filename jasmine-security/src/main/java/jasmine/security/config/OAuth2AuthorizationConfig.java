package jasmine.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    /** 密码编码器 */
    private final PasswordEncoder passwordEncoder;

    public OAuth2AuthorizationConfig(AuthenticationManager authenticationManager,
                                     PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // TODO 这里硬编码个客户端，实际开发时需要重写这个方法
        clients.inMemory()
                .withClient("MoLiHua")
                .secret(passwordEncoder.encode("123456"))
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                // "ALL"是自定义，没有特殊含义
                .scopes("ALL")
                .accessTokenValiditySeconds(3600);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 设置认证管理器
        endpoints.authenticationManager(authenticationManager);
    }

}
