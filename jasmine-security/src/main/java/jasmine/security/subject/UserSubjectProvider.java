package jasmine.security.subject;

import jasmine.core.context.InitSupport;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.context.SubjectProvider;
import jasmine.security.subject.UserSubject;
import jasmine.security.subject.UserSubjectDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * <p>
 * 表示当前用户。
 * </p>
 *
 * @author mh.z
 */
@Component
public class UserSubjectProvider implements SubjectProvider, InitSupport {
    private UserSubjectDetailsService subjectDetailsService;

    @Override
    public void init(RuntimeProvider provider) {
        this.subjectDetailsService = provider.getByType(UserSubjectDetailsService.class);
    }

    @Override
    public Long getCurrentUserId() {
        UserSubject subject = getSubjectFromContext();

        if (subject != null) {
            return subject.getUserId();
        }

        return null;
    }

    @Override
    public Long getCurrentTenantId() {
        UserSubject subject = getSubjectFromContext();

        if (subject != null) {
            return subject.getTenantId();
        }

        return null;
    }

    @Override
    public Object getCurrentSubject() {
        return getSubjectFromContext();
    }

    @Override
    public void setCurrentSubject(Long tenantId, Long userId) {
        UserSubject userSubject = null;

        if (userId != null) {
            userSubject = subjectDetailsService.loadUserByUserId(userId);
        } else if (tenantId != null) {
            userSubject = new UserSubject(tenantId, -1L);
        }

        if (userSubject == null) {
            userSubject = new UserSubject(-1L, -1L);
        }

        setSubjectToContext(userSubject);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    protected UserSubject getSubjectFromContext() {
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

    /**
     * 设置用户信息
     *
     * @param subject
     */
    protected void setSubjectToContext(UserSubject subject) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(subject,
                null, Collections.emptyList());

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
    }

}
