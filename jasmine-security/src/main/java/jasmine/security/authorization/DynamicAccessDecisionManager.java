package jasmine.security.authorization;

import jasmine.security.subject.UserSubject;
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
import java.util.Collection;
import java.util.Collections;

/**
 * @author mh.z
 */
@Component
public class DynamicAccessDecisionManager extends AffirmativeBased {
    private static final Logger logger = LoggerFactory.getLogger(DynamicAccessDecisionManager.class);
    private CheckUserPermissionService checkUserPermissionService;

    public DynamicAccessDecisionManager(CheckUserPermissionService checkUserPermissionService) {
        super(Collections.singletonList(new WebExpressionVoter()));
        this.checkUserPermissionService = checkUserPermissionService;
    }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        super.decide(authentication, object, configAttributes);

        Object principal = authentication.getPrincipal();
        HttpServletRequest request = ((FilterInvocation) object).getRequest();

        if (principal instanceof UserSubject) {
            UserSubject subject = (UserSubject) principal;
            boolean checkResult = checkUserPermissionService.check(subject, request);

            if (!checkResult) {
                String message = messages.getMessage("AbstractAccessDecisionManager.accessDenied",
                        "Access is denied");
                throw new AccessDeniedException(message);
            }
        }

        logger.debug("access [{}]{}", request.getMethod(), request.getRequestURI());
    }

}