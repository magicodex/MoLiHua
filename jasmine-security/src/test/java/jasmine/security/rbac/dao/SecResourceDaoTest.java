package jasmine.security.rbac.dao;

import jasmine.framework.common.util.CollectionUtil;
import jasmine.framework.test.context.AppTestContext;
import jasmine.security.rbac.mapper.SecResourceMapper;
import jasmine.security.rbac.model.SecResource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class SecResourceDaoTest extends AppTestContext {
    @Autowired
    private SecResourceMapper secResourceMapper;

    @Test
    public void testListResourcesByPathNoI18n() {
        SecResourceDAO dao = new SecResourceDAO(secResourceMapper);

        // 有这个路径
        {
            List<SecResource> actualList = dao.listResourcesByPathNoI18n("/api/test/path1");
            Assert.assertNotNull(actualList);
            Assert.assertEquals(1, actualList.size());

            Map<Long, SecResource> actualMap = CollectionUtil.toMap(actualList, SecResource::getId);
            Assert.assertTrue(actualMap.containsKey(100001L));
        }

        // 没有这个路径
        {
            List<SecResource> actualList = dao.listResourcesByPathNoI18n("/api/test/unknown");
            Assert.assertNotNull(actualList);
            Assert.assertEquals(0, actualList.size());
        }
    }

}
