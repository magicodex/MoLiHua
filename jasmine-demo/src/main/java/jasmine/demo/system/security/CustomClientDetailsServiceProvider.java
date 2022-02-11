package jasmine.demo.system.security;

import jasmine.core.context.RuntimeProvider;
import jasmine.security.subject.ClientDetailsServiceProvider;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author mh.z
 */
@Service
public class CustomClientDetailsServiceProvider implements ClientDetailsServiceProvider {
    private RuntimeProvider runtimeProvider;

    public CustomClientDetailsServiceProvider(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }

    @Override
    public ClientDetailsService getService() {
        return new CustomClientDetailsService(runtimeProvider);
    }

}
