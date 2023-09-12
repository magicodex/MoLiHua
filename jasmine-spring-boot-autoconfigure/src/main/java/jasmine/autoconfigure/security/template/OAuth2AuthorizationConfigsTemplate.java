package jasmine.autoconfigure.security.template;

import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author mh.z
 */
public interface OAuth2AuthorizationConfigsTemplate {

    /**
     * 修改配置
     *
     * @param security
     * @throws Exception
     */
    void configure(AuthorizationServerSecurityConfigurer security) throws Exception;

    /**
     * 修改配置
     *
     * @param clients
     * @throws Exception
     */
    void configure(ClientDetailsServiceConfigurer clients) throws Exception;

    /**
     * 修改配置
     *
     * @param endpoints
     * @throws Exception
     */
    void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception;
}
