package jasmine.autoconfigure.security.rbac;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class RbacProperties {

    /** 是否启用 RBAC 访问控制 */
    @Value("${jasmine.security.rbac.enabled:false}")
    private Boolean rbacEnabled;

    public Boolean getRbacEnabled() {
        return rbacEnabled;
    }

}
