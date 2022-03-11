package jasmine.autoconfigure.framework.database;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mh.z
 */
@ConfigurationProperties(prefix = "jasmine.data")
public class MybatisPlusProperties {
    /** 租户相关配置 */
    private Tenant tenant = new Tenant();

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    /**
     *
     */
    public static class Tenant {
        /** 是否启用租户拦截器 */
        private Boolean enabled = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    }

}
