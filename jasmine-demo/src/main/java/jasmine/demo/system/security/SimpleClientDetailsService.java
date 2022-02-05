package jasmine.demo.system.security;

import jasmine.core.context.RuntimeProvider;
import jasmine.demo.authentication.persistent.dao.UserDao;
import jasmine.demo.authentication.persistent.entity.UserEO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Arrays;

/**
 * @author mh.z
 */
public class SimpleClientDetailsService implements ClientDetailsService {
    private RuntimeProvider runtimeProvider;
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    public SimpleClientDetailsService(RuntimeProvider provider) {
        this.runtimeProvider = provider;
        userDao = runtimeProvider.getByType(UserDao.class);
        passwordEncoder = runtimeProvider.getByType(PasswordEncoder.class);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        UserEO user = userDao.getUserByName(clientId);
        if (user == null) {
            return null;
        }

        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId(clientId);
        clientDetails.setClientSecret(passwordEncoder.encode(user.getPassword()));
        clientDetails.setAuthorizedGrantTypes(Arrays.asList("authorization_code", "password", "refresh_token"));
        // "ALL"是自定义，没有特殊含义
        clientDetails.setScope(Arrays.asList("ALL"));
        clientDetails.setAccessTokenValiditySeconds(3600);

        return clientDetails;
    }

}
