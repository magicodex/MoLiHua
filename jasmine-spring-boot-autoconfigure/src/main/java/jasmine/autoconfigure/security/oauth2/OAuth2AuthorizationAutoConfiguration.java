package jasmine.autoconfigure.security.oauth2;

import jasmine.security.config.JasmineSecurityConfig;
import jasmine.security.subject.ClientSubjectDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
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
@ConditionalOnClass(JasmineSecurityConfig.class)
@EnableAuthorizationServer
@Configuration
public class OAuth2AuthorizationAutoConfiguration extends AuthorizationServerConfigurerAdapter {
    /** 认证管理器 */
    private final AuthenticationManager authenticationManager;

    private ClientSubjectDetailsService clientSubjectDetailsService;

    private static final String TOKEN_KEY_ACCESS = "permitAll()";
    private static final String CHECK_TOKEN_ACCESS = "isAuthenticated()";

    public OAuth2AuthorizationAutoConfiguration(AuthenticationManager authenticationManager,
                                                @Autowired(required = false) ClientSubjectDetailsService clientSubjectDetailsService) {
        this.authenticationManager = authenticationManager;
        this.clientSubjectDetailsService = clientSubjectDetailsService;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 开启 /oauth/token_key 验证端口成无权限访问
        security.tokenKeyAccess(TOKEN_KEY_ACCESS)
                // 开启 /oauth/check_token 验证端口成认证权限访问
                .checkTokenAccess(CHECK_TOKEN_ACCESS)
                // 主要是让 /oauth/token 支持 client_id 以及 client_secret 做登录认证
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        if (clientSubjectDetailsService != null) {
            clients.withClientDetails(clientSubjectDetailsService);
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
