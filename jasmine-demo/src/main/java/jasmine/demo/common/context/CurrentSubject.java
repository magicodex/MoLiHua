package jasmine.demo.common.context;

import jasmine.security.subject.UserSubject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>
 * 表示当前用户。
 * </p>
 *
 * @author mh.z
 */
public class CurrentSubject {

    /**
     * 返回用户ID
     *
     * @return
     */
    public static Long getUserId() {
        UserSubject subject = getSubjectFromContext();

        if (subject != null) {
            return subject.getUserId();
        }

        return null;
    }

    /**
     * 返回租户ID
     *
     * @return
     */
    public static Long getTenantId() {
        UserSubject subject = getSubjectFromContext();

        if (subject != null) {
            return subject.getTenantId();
        }

        return null;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    protected static UserSubject getSubjectFromContext() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }

        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return null;
        }

        if (authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserSubject) {
                return (UserSubject) principal;
            }
        }

        return null;
    }

}
