package jasmine.framework.context.impl;

import jasmine.framework.context.SubjectProvider;

/**
 * @author mh.z
 */
public class FixedSubjectProvider implements SubjectProvider {
    /** 用户ID */
    private Long userId;
    /** 租户ID */
    private Long tenantId;

    public static final Long DEFAULT_USER_ID = -1L;
    public static final Long DEFAULT_TENANT_ID = -1L;

    public FixedSubjectProvider() {
        this(DEFAULT_USER_ID, DEFAULT_TENANT_ID);
    }

    public FixedSubjectProvider(Long userId, Long tenantId) {
        this.userId = userId;
        this.tenantId = tenantId;
    }

    @Override
    public Long getCurrentUserId() {
        return userId;
    }

    @Override
    public Long getCurrentTenantId() {
        return tenantId;
    }

    @Override
    public Object getCurrentSubject() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCurrentSubject(Long tenantId, Long userId) {
        this.tenantId = tenantId;
        this.userId = userId;
    }

}
