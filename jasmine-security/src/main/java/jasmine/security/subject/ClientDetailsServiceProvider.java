package jasmine.security.subject;

import org.springframework.security.oauth2.provider.ClientDetailsService;

/**
 * @author mh.z
 */
public interface ClientDetailsServiceProvider {

    /**
     * 返回对象
     *
     * @return
     */
    ClientDetailsService getService();
}
