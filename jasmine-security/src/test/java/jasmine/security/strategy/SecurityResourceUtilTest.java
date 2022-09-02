package jasmine.security.strategy;

import jasmine.security.constant.SecurityConstants;
import jasmine.security.rbac.dto.SecResourceBaseInfoDTO;
import jasmine.security.rbac.model.SecResource;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class SecurityResourceUtilTest {

    @Test
    public void testToResourceBaseInfoDTO() {
        SecResource secResource = new SecResource();
        secResource.setId(1L);
        secResource.setResourceType(SecurityConstants.RESOURCE_TYPE_API);
        secResource.setAccessMethod(SecurityConstants.RESOURCE_ACCESS_METHOD_ANY);
        secResource.setAccessPolicy(SecurityConstants.RESOURCE_ACCESS_POLICY_ANONYMOUS);
        secResource.setResourcePath("/test/path");
        secResource.setFrozenFlag(false);
        secResource.setRemark("Hello, test!");

        SecResourceBaseInfoDTO actual = SecurityResourceUtil.toResourceBaseInfoDTO(secResource);
        Assert.assertNotNull(actual);
        Assert.assertEquals(Long.valueOf(1L), actual.getResourceId());
        Assert.assertEquals(SecurityConstants.RESOURCE_TYPE_API, actual.getResourceType());
        Assert.assertEquals(SecurityConstants.RESOURCE_ACCESS_METHOD_ANY, actual.getAccessMethod());
        Assert.assertEquals(SecurityConstants.RESOURCE_ACCESS_POLICY_ANONYMOUS, actual.getAccessPolicy());
        Assert.assertEquals("/test/path", actual.getResourcePath());
        Assert.assertEquals(false, actual.getFrozenFlag());
    }

}
