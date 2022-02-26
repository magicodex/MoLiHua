package jasmine.security.authorization;

import jasmine.core.util.QCheckUtil;
import jasmine.security.subject.UserSubject;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author mh.z
 */
public class DynamicAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {
    /** 是否启用 RBAC 访问控制 */
    private Boolean rbacEnabled;
    /** 访问决策策略 */
    private AccessDecisionStrategy accessDecisionStrategy;

    public DynamicAccessDecisionVoter(Boolean rbacEnabled, AccessDecisionStrategy accessDecisionStrategy) {
        this.rbacEnabled = rbacEnabled;
        this.accessDecisionStrategy = accessDecisionStrategy;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation invocation,
                    Collection<ConfigAttribute> attributes) {
        QCheckUtil.notNull(authentication, "authentication null");
        QCheckUtil.notNull(invocation, "invocation null");
        Object principal = authentication.getPrincipal();

        // 若未开启 RBAC 访问控制则根据是否认证决定是否允许访问
        if (!Boolean.TRUE.equals(rbacEnabled)) {
            if (authentication.isAuthenticated()) {
                return AccessDecisionVoter.ACCESS_GRANTED;
            }

            return AccessDecisionVoter.ACCESS_DENIED;
        }

        if (accessDecisionStrategy == null) {
            return AccessDecisionVoter.ACCESS_GRANTED;
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