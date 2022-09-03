package jasmine.security.strategy;

import jasmine.framework.test.context.AppTestContext;
import jasmine.security.constant.SecurityConstants;
import jasmine.security.rbac.dao.SecFunctionDAO;
import jasmine.security.rbac.dao.SecResourceDAO;
import jasmine.security.rbac.dto.SecResourceBaseInfoDTO;
import jasmine.security.rbac.mapper.SecFunctionMapper;
import jasmine.security.rbac.mapper.SecResourceMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class DefaultRbacQueryServiceTest extends AppTestContext {
    @Autowired
    private SecResourceMapper resourceMapper;
    @Autowired
    private SecFunctionMapper functionMapper;

    @Test
    public void testQueryResourceByRequest() {
        DefaultRbacQueryService service = getTestService();

        SecResourceBaseInfoDTO actual = service.queryResourceByRequest(
                SecurityConstants.RESOURCE_ACCESS_METHOD_GET, "/api/test/path1");
        Assert.assertNotNull(actual);
        Assert.assertEquals(Long.valueOf(100001L), actual.getResourceId());
    }

    @Test
    public void testQueryFunctionsByUser() {
        DefaultRbacQueryService service = getTestService();

        // 角色1和角色2被授权功能加起来有功能1和功能2
        {
            List<Long> actualList = service.queryFunctionsByUser(101001L, Arrays.asList(100001L, 100002L));
            Assert.assertNotNull(actualList);

            Assert.assertEquals(2, actualList.size());
            Assert.assertTrue(actualList.contains(100001L));
            Assert.assertTrue(actualList.contains(100002L));
        }

        // 角色1被授权了功能1和功能2
        {
            List<Long> actualList = service.queryFunctionsByUser(101001L, Arrays.asList(100001L, 100002L));
            Assert.assertNotNull(actualList);

            Assert.assertEquals(2, actualList.size());
            Assert.assertTrue(actualList.contains(100001L));
            Assert.assertTrue(actualList.contains(100002L));
        }

        // 角色2被授权了功能2
        {
            List<Long> actualList = service.queryFunctionsByUser(101001L, Arrays.asList(100002L));
            Assert.assertNotNull(actualList);

            Assert.assertEquals(1, actualList.size());
            Assert.assertTrue(actualList.contains(100002L));
        }
    }

    @Test
    public void tesQueryFunctionsByResource() {
        DefaultRbacQueryService service = getTestService();

        // 资源1只授权给功能1
        {
            List<Long> actualList = service.queryFunctionsByResource(100001L);
            Assert.assertNotNull(actualList);

            Assert.assertEquals(1, actualList.size());
            Assert.assertTrue(actualList.contains(100001L));
        }

        // 资源5授权给了功能2
        {
            List<Long> actualList = service.queryFunctionsByResource(100005L);
            Assert.assertNotNull(actualList);

            Assert.assertEquals(1, actualList.size());
            Assert.assertTrue(actualList.contains(100002L));
        }

        // 资源7只授权给功能2
        {
            List<Long> actualList = service.queryFunctionsByResource(100007L);
            Assert.assertNotNull(actualList);

            Assert.assertEquals(1, actualList.size());
            Assert.assertTrue(actualList.contains(100002L));
        }

        // 资源8未授权给功能
        {
            List<Long> actualList = service.queryFunctionsByResource(100008L);
            Assert.assertNotNull(actualList);
            Assert.assertEquals(0, actualList.size());
        }
    }

    /**
     * 返回测试对象
     *
     * @return
     */
    private DefaultRbacQueryService getTestService() {
        SecResourceDAO resourceDAO = new SecResourceDAO(resourceMapper);
        SecFunctionDAO functionDAO = new SecFunctionDAO(functionMapper);
        DefaultRbacQueryService testService = new DefaultRbacQueryService(functionDAO, resourceDAO);

        return testService;
    }

}
