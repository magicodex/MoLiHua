package jasmine.security.rbac.mapper;

import jasmine.framework.test.context.AppTestContext;
import jasmine.security.rbac.model.SecRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class SecRoleMapperTest extends AppTestContext {
    @Autowired
    private SecRoleMapper testMapper;

    @Test
    public void testListAllTenantRolesByUserIdNoI18n() {
        List<SecRole> roleList = testMapper.listAllTenantRolesByUserIdNoI18n(100001L);

        Assert.assertNotNull(roleList);
    }

}
