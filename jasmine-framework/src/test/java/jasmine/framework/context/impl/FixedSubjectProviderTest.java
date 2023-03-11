package jasmine.framework.context.impl;

import jasmine.framework.context.impl.FixedSubjectProvider;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class FixedSubjectProviderTest {

    @Test
    public void test() {
        FixedSubjectProvider provider = new FixedSubjectProvider();
        Assert.assertEquals(FixedSubjectProvider.DEFAULT_USER_ID, provider.getCurrentUserId());
        Assert.assertEquals(FixedSubjectProvider.DEFAULT_TENANT_ID, provider.getCurrentTenantId());

        provider.setCurrentSubject(1L, 100001L);
        Assert.assertEquals(Long.valueOf(1), provider.getCurrentTenantId());
        Assert.assertEquals(Long.valueOf(100001L), provider.getCurrentUserId());
    }

}
