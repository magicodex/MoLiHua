package jasmine.security.authorization;

import jasmine.core.util.QStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author mh.z
 */
public class AccessDecisionManagerProxy implements AccessDecisionManager {
    private static final Logger logger = LoggerFactory.getLogger(AccessDecisionManagerProxy.class);
    /** 决策访问控制器 */
    private AccessDecisionManager accessDecisionManager;

    public AccessDecisionManagerProxy(AccessDecisionManager accessDecisionManager) {
        this.accessDecisionManager = accessDecisionManager;
    }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        String requestMethod = QStringUtil.orEmpty(request.getMethod());
        String requestURI = request.getRequestURI();

        try {
            accessDecisionManager.decide(authentication, object, configAttributes);

            logger.debug("access [{}]{}", requestMethod, requestURI);
        } catch (AccessDeniedException e) {
            logger.debug("access denied [{}]{}", requestMethod, requestURI);
            throw e;
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return accessDecisionManager.supports(attribute);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return accessDecisionManager.supports(clazz);
    }

}
