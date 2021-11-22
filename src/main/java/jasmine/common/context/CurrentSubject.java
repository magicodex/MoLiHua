package jasmine.common.context;

import jasmine.framework.security.UserSubject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentSubject {

    public static Long getUserId() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }

        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return null;
        }

        if (!authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserSubject)) {
            return null;
        }

        UserSubject subject = (UserSubject) principal;
        Long userId = subject.getUserId();

        return userId;
    }

}
