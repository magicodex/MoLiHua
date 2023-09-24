package jasmine.framework.database.liquibase;

import jasmine.framework.common.util.ErrorUtil;
import jasmine.framework.context.CurrentSubject;
import jasmine.framework.context.CustomInitializingSingleton;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;

/**
 * @author mh.z
 */
public class CustomSpringLiquibase extends SpringLiquibase implements CustomInitializingSingleton {
    /** 默认用户ID */
    private Long defaultUserId;
    /** 默认租户ID */
    private Long defaultTenantId;

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

    @Override
    public void afterPropertiesSet() throws LiquibaseException {
        //
    }

    @Override
    public void afterSingletonsInstantiated() {
        try {
            CurrentSubject.setSubject(defaultTenantId, defaultUserId);
            super.afterPropertiesSet();
        } catch (LiquibaseException e) {
            throw ErrorUtil.sneakyError(e);
        } finally {
            CurrentSubject.setSubject(null, null);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
