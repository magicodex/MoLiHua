package jasmine.security.authorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author mh.z
 */
@Component
public class DynamicAccessDecisionManager extends AffirmativeBased {
    private static final Logger logger = LoggerFactory.getLogger(DynamicAccessDecisionManager.class);
    private DynamicAccessDecisionVoter dynamicAccessDecisionVoter;

    public DynamicAccessDecisionManager(DynamicAccessDecisionVoter dynamicAccessDecisionVoter) {
        super(Arrays.asList(new WebExpressionVoter(), dynamicAccessDecisionVoter));
    }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();

        try {
            super.decide(authentication, object, configAttributes);

            logger.debug("access [{}]{}", request.getMethod(), request.getRequestURI());
        } catch (AccessDeniedException e) {
            logger.debug("access denied [{}]{}", request.getMethod(), request.getRequestURI());
            throw e;
        }
    }

}
