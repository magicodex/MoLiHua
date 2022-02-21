package jasmine.security.authorization;

import jasmine.core.util.QCheckUtil;
import jasmine.security.config.JasmineSecurityConfig;
import jasmine.security.subject.UserSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author mh.z
 */
@Component
public class DynamicAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {
    private JasmineSecurityConfig securityConfig;
    private DynamicAccessCheckService checkService;

    public DynamicAccessDecisionVoter(JasmineSecurityConfig securityConfig,
                                      @Autowired(required = false) DynamicAccessCheckService checkService) {
        this.securityConfig = securityConfig;
        this.checkService = checkService;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation invocation,
                    Collection<ConfigAttribute> attributes) {
        QCheckUtil.notNull(authentication, "authentication null");
        QCheckUtil.notNull(invocation, "invocation null");
        Object principal = authentication.getPrincipal();

        if (checkService == null
                || !Boolean.TRUE.equals(securityConfig.getRbacEnabled())) {
            return AccessDecisionVoter.ACCESS_GRANTED;
        }

        if (principal instanceof UserSubject) {
            UserSubject subject = (UserSubject) principal;
            HttpServletRequest request = invocation.getRequest();
            boolean checkResult = checkService.check(subject, request);

            if (checkResult) {
                return AccessDecisionVoter.ACCESS_GRANTED;
            }
        }

        return AccessDecisionVoter.ACCESS_DENIED;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

}
