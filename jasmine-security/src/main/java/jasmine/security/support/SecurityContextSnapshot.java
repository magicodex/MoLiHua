package jasmine.security.support;

import jasmine.framework.concurrent.ContextSnapshot;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author mh.z
 */
public class SecurityContextSnapshot implements ContextSnapshot {
    private SecurityContext securityContext;

    public SecurityContextSnapshot() {
        //
    }

    public SecurityContext getSecurityContext() {
        return securityContext;
    }

    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @Override
    public void setToCurrentThread() {
        SecurityContext targetSecurityContext = SecurityContextHolder.getContext();
        targetSecurityContext.setAuthentication(securityContext.getAuthentication());
    }

    @Override
    public void clearFromCurrentThread() {
        SecurityContextHolder.clearContext();
    }

}
