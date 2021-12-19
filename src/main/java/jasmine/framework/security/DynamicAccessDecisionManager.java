package jasmine.framework.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author mh.z
 */
@Component
public class DynamicAccessDecisionManager implements AccessDecisionManager {
    private static final Logger logger = LoggerFactory.getLogger(DynamicAccessDecisionManager.class);

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if (!authentication.isAuthenticated()) {
            throw new AccessDeniedException("access denied");
        }

        // TODO
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        logger.debug("access {}", request.getRequestURI());
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
