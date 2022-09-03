package jasmine.security.rbac.dao;

import jasmine.framework.test.context.AppTestContext;
import jasmine.security.rbac.mapper.SecRoleMapper;
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
public class SecRoleDaoTest extends AppTestContext {
    @Autowired
    private SecRoleMapper secRoleMapper;

    @Test
    public void testListAllTenantRolesByUserIdNoI18n() {
        SecRoleDAO dao = new SecRoleDAO(secRoleMapper);

        List<SecRole> actualList = dao.listAllTenantRolesByUserIdNoI18n(101001L);
        Assert.assertNotNull(actualList);
        Assert.assertEquals(2, actualList.size());
    }

}
