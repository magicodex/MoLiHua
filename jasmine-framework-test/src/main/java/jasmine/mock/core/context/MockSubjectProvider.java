package jasmine.mock.core.context;

import jasmine.framework.context.SubjectProvider;

/**
 * @author mh.z
 */
public class MockSubjectProvider implements SubjectProvider {
    private Long currentUserId;
    private Long currentTenantId;

    public MockSubjectProvider() {
        this.currentUserId = -1L;
        this.currentTenantId = -1L;
    }

    public MockSubjectProvider(Long currentUserId, Long currentTenantId) {
        this.currentUserId = currentUserId;
        this.currentTenantId = currentTenantId;
    }

    public void reset() {
        currentUserId = -1L;
        currentTenantId = -1L;
    }

    @Override
    public Long getCurrentUserId() {
        return currentUserId;
    }

    @Override
    public Long getCurrentTenantId() {
        return currentTenantId;
    }

    @Override
    public Object getCurrentSubject() {
        return null;
    }

    @Override
    public void setCurrentSubject(Long tenantId, Long userId) {
        this.currentUserId = userId;
        this.currentTenantId = tenantId;
    }

}
