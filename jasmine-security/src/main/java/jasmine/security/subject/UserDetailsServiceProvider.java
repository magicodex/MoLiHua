package jasmine.security.subject;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author mh.z
 */
public interface UserDetailsServiceProvider {

    UserDetailsService getService();
}
