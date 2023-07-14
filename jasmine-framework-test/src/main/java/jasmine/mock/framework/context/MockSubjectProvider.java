package jasmine.mock.framework.context;

import jasmine.framework.context.SubjectProvider;
import jasmine.framework.test.constant.TestConstants;

/**
 * @author mh.z
 */
public class MockSubjectProvider implements SubjectProvider {
    private Long currentUserId;
    private Long currentTenantId;

    public MockSubjectProvider() {
        this.currentUserId = TestConstants.TEST_USER_ID_UNKNOWN;
        this.currentTenantId = TestConstants.TEST_TENANT_ID_UNKNOWN;
    }

    public MockSubjectProvider(Long currentUserId, Long currentTenantId) {
        this.currentUserId = currentUserId;
        this.currentTenantId = currentTenantId;
    }

    public void reset() {
        currentUserId = TestConstants.TEST_USER_ID_UNKNOWN;
        currentTenantId = TestConstants.TEST_TENANT_ID_UNKNOWN;
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
