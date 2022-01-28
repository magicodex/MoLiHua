package jasmine.demo.system.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author mh.z
 */
@Service
public class BaseClientDetailsService implements ClientDetailsService {
    private PasswordEncoder passwordEncoder;

    public BaseClientDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        if ("MoLiHua".equals(clientId)) {
            BaseClientDetails clientDetails = new BaseClientDetails();
            clientDetails.setClientId("MoLiHua");
            clientDetails.setClientSecret(passwordEncoder.encode("123456"));
            clientDetails.setAuthorizedGrantTypes(Arrays.asList("authorization_code", "password", "refresh_token"));
            // "ALL"是自定义，没有特殊含义
            clientDetails.setScope(Arrays.asList("ALL"));
            clientDetails.setAccessTokenValiditySeconds(3600);

            return clientDetails;
        }

        return null;
    }

}
