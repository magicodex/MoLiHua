package jasmine.demo.system.security;

import jasmine.core.context.RuntimeProvider;
import jasmine.security.subject.UserDetailsServiceProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author mh.z
 */
@Service
public class CustomUserDetailsServiceProvider implements UserDetailsServiceProvider {
    private RuntimeProvider runtimeProvider;

    public CustomUserDetailsServiceProvider(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }

    @Override
    public UserDetailsService getService() {
        return new CustomUserDetailsService(runtimeProvider);
    }

}
