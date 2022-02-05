package jasmine.demo.system.security;

import jasmine.security.subject.ClientDetailsServiceProvider;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author mh.z
 */
@Service
public class ClientDetailsServiceProviderBean implements ClientDetailsServiceProvider {
    private SimpleClientDetailsService clientDetailsService;

    public ClientDetailsServiceProviderBean(SimpleClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @Override
    public ClientDetailsService getService() {
        return clientDetailsService;
    }

}
