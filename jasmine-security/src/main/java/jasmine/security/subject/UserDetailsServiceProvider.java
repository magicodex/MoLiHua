package jasmine.security.subject;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author mh.z
 */
public interface UserDetailsServiceProvider {

    /**
     * 返回对象
     *
     * @return
     */
    UserDetailsService getService();
}
