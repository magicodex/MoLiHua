package jasmine.demo.framework.context;

import org.springframework.core.task.TaskDecorator;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author mh.z
 */
public class AsyncTaskContextDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        Runnable proxy = () -> {
            try {
                // 复制上下文
                SecurityContext targetSecurityContext = SecurityContextHolder.getContext();
                targetSecurityContext.setAuthentication(securityContext.getAuthentication());
                RequestContextHolder.setRequestAttributes(requestAttributes);

                runnable.run();
            } finally {
                // 重置上下文
                SecurityContextHolder.clearContext();
                RequestContextHolder.resetRequestAttributes();
            }
        };

        return proxy;
    }

}
