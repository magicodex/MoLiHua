package jasmine.security.authorization;

import jasmine.framework.common.util.CheckUtil;
import jasmine.security.subject.UserSubject;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author mh.z
 */
public class DynamicAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {
    /** 访问决策策略 */
    private AccessDecisionStrategy accessDecisionStrategy;

    public DynamicAccessDecisionVoter(AccessDecisionStrategy accessDecisionStrategy) {
        this.accessDecisionStrategy = accessDecisionStrategy;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation invocation,
                    Collection<ConfigAttribute> attributes) {
        CheckUtil.notNull(authentication, "authentication null");
        CheckUtil.notNull(invocation, "invocation null");
        Object principal = authentication.getPrincipal();

        // 若未指定访问策略则根据是否认证决定是否允许访问
        if (accessDecisionStrategy == null) {
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                return AccessDecisionVoter.ACCESS_GRANTED;
            }

            return AccessDecisionVoter.ACCESS_DENIED;
        }

        if (principal instanceof UserSubject) {
            UserSubject subject = (UserSubject) principal;
            HttpServletRequest request = invocation.getRequest();
            boolean checkResult = accessDecisionStrategy.check(subject, request);

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
