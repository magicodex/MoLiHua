package jasmine.security.authorization;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author mh.z
 */
public class FilterSecurityInterceptorPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {
    private AccessDecisionManager accessDecisionManager;

    public FilterSecurityInterceptorPostProcessor(AccessDecisionManager accessDecisionManager) {
        this.accessDecisionManager = accessDecisionManager;
    }

    @Override
    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
        object.setAccessDecisionManager(accessDecisionManager);

        return object;
    }

}
