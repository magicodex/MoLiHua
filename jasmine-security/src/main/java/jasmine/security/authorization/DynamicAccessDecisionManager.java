package jasmine.security.authorization;

import jasmine.core.util.QCheckUtil;
import jasmine.security.config.JasmineSecurityConfig;
import jasmine.security.subject.UserSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private JasmineSecurityConfig securityConfig;
    private RbacCheckService rbacService;

    public DynamicAccessDecisionManager(JasmineSecurityConfig securityConfig,
                                        @Autowired(required = false) RbacCheckService rbacService) {
        super(Collections.singletonList(new WebExpressionVoter()));
        this.securityConfig = securityConfig;
        this.rbacService = rbacService;
    }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        super.decide(authentication, object, configAttributes);
        HttpServletRequest request = ((FilterInvocation) object).getRequest();

        if (Boolean.TRUE.equals(securityConfig.getRbacEnabled())) {
            Object principal = authentication.getPrincipal();
            rbacCheck(principal, request);
        }

        logger.debug("access [{}]{}", request.getMethod(), request.getRequestURI());
    }

    /**
     * 检查权限
     *
     * @param principal
     * @param request
     */
    protected void rbacCheck(Object principal, HttpServletRequest request) {
        QCheckUtil.notNullProp(rbacService, "rbacService null");

        if (principal instanceof UserSubject) {
            UserSubject subject = (UserSubject) principal;
            boolean checkResult = rbacService.check(subject, request);

            if (!checkResult) {
                logger.debug("access denied [{}]{}", request.getMethod(), request.getRequestURI());

                String message = messages.getMessage("AbstractAccessDecisionManager.accessDenied",
                        "Access is denied");
                throw new AccessDeniedException(message);
            }
        }
    }

}
