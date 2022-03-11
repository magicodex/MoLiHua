package jasmine.autoconfigure.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mh.z
 */
@ConfigurationProperties(prefix = "jasmine.security")
public class JasmineSecurityProperties {
    /** RBAC相关配置 */
    private Rbac rbac = new Rbac();

    public Rbac getRbac() {
        return rbac;
    }

    public void setRbac(Rbac rbac) {
        this.rbac = rbac;
    }

    /**
     * RBAC相关配置
     */
    public static class Rbac {
        /** 是否启用RBAC访问控制 */
        private Boolean enabled = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    }

}
