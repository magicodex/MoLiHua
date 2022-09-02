package jasmine.security.support;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

/**
 * @author mh.z
 */
public class SecurityContextUtil {

    /**
     * 返回当前的认证信息
     *
     * @return
     */
    public static Authentication getCurrentAuthentication() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = null;

        if (securityContext != null) {
            authentication = securityContext.getAuthentication();
        }

        return authentication;
    }

    /**
     * 设置当前的认证信息
     *
     * @param authentication
     */
    public static void setCurrentAuthentication(Authentication authentication) {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        if (securityContext != null) {
            securityContext.setAuthentication(authentication);
        } else {
            securityContext = new SecurityContextImpl(authentication);
            SecurityContextHolder.setContext(securityContext);
        }
    }

}
