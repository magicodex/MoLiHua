package jasmine.mock.context;

import jasmine.core.context.SubjectProvider;

/**
 * @author mh.z
 */
public class TestSubjectProvider implements SubjectProvider {
    private Long currentUserId;
    private Long currentTenantId;

    public TestSubjectProvider() {
        this.currentUserId = -1L;
        this.currentTenantId = -1L;
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
