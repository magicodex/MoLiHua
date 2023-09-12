package jasmine.autoconfigure.security.oauth2;

import jasmine.autoconfigure.security.template.OAuth2AuthorizationConfigsTemplate;
import jasmine.framework.common.util.SpringUtil;
import jasmine.security.subject.ClientSubjectDetailsService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author mh.z
 */
public class OAuth2AuthorizationConfiguration implements OAuth2AuthorizationConfigsTemplate, ApplicationContextAware {
    protected ApplicationContext applicationContext;

    private static final String TOKEN_KEY_ACCESS = "permitAll()";
    private static final String CHECK_TOKEN_ACCESS = "isAuthenticated()";

    public OAuth2AuthorizationConfiguration() {
        //
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
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
        ClientSubjectDetailsService clientSubjectDetailsService = SpringUtil.getBean(applicationContext,
                ClientSubjectDetailsService.class, false);

        if (clientSubjectDetailsService != null) {
            clients.withClientDetails(clientSubjectDetailsService);
        } else {
            clients.withClientDetails(new InMemoryClientDetailsService());
        }
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        AuthenticationManager authenticationManager = SpringUtil.getBean(applicationContext,
                AuthenticationManager.class, true);
        TokenStore tokenStore = SpringUtil.getBean(applicationContext, TokenStore.class, false);

        // 设置认证管理器
        endpoints.authenticationManager(authenticationManager);

        // 自定义令牌存储方式
        if (tokenStore != null) {
            endpoints.tokenStore(tokenStore);
        }
    }

}

