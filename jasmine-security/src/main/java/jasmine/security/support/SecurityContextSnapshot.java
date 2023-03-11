package jasmine.security.support;

import jasmine.framework.context.thread.ContextSnapshot;
import org.springframework.security.core.Authentication;

/**
 * @author mh.z
 */
public class SecurityContextSnapshot implements ContextSnapshot {
    private Authentication authentication;

    public SecurityContextSnapshot(Authentication authentication) {
        this.authentication = authentication;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    @Override
    public void copyToCurrentThread() {
        SecurityContextUtil.setCurrentAuthentication(authentication);
    }

}
