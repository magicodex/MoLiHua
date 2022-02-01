package jasmine.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class JasmineSecurityConfig {

    /** 是否启用RBAC访问控制 */
    @Value("${jasmine.security.rbac.enabled:false}")
    private Boolean rbacEnabled;

    public Boolean getRbacEnabled() {
        return rbacEnabled;
    }

}
