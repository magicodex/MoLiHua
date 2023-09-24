package jasmine.autoconfigure.framework.database;

import jasmine.framework.context.CurrentSubject;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mh.z
 */
@ConfigurationProperties(prefix = "jasmine.init-data")
public class InitDataProperties {
    /** 默认用户ID */
    private Long defaultUserId = CurrentSubject.DEFAULT_USER_ID;
    /** 默认租户ID */
    private Long defaultTenantId = CurrentSubject.DEFAULT_TENANT_ID;

    public Long getDefaultUserId() {
        return defaultUserId;
    }

    public void setDefaultUserId(Long defaultUserId) {
        this.defaultUserId = defaultUserId;
    }

    public Long getDefaultTenantId() {
        return defaultTenantId;
    }

    public void setDefaultTenantId(Long defaultTenantId) {
        this.defaultTenantId = defaultTenantId;
    }

}
