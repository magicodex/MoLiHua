package jasmine.demo.system.security;

import jasmine.core.context.RuntimeProvider;
import jasmine.security.subject.UserDetailsServiceProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceProviderImpl implements UserDetailsServiceProvider {
    private RuntimeProvider runtimeProvider;

    public UserDetailsServiceProviderImpl(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }

    @Override
    public UserDetailsService getService() {
        return new DemoUserDetailsService(runtimeProvider);
    }

}
