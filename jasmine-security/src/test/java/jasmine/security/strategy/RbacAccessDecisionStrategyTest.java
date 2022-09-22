package jasmine.security.strategy;

import jasmine.framework.test.context.AppTestContext;
import jasmine.security.authorization.RoleAuthority;
import jasmine.security.rbac.dao.SecFunctionDAO;
import jasmine.security.rbac.dao.SecResourceDAO;
import jasmine.security.rbac.dto.SecResourceBaseInfoDTO;
import jasmine.security.rbac.mapper.SecFunctionMapper;
import jasmine.security.rbac.mapper.SecResourceMapper;
import jasmine.security.rbac.model.SecResource;
import jasmine.security.subject.UserSubject;
import jasmine.core.test.constant.TestConstants;
import jasmine.testconfigure.security.MockUrlPatternMatcher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class RbacAccessDecisionStrategyTest extends AppTestContext {
    @Autowired
    private SecResourceMapper resourceMapper;
    @Autowired
    private SecFunctionMapper functionMapper;

    @Test
    public void testCheck() {
        RbacAccessDecisionStrategy strategy = createTestObject();

        Collection<GrantedAuthority> authorities = Arrays.asList(
                new RoleAuthority(100001L, "ROLE1"),
                new RoleAuthority(100002L, "ROLE1"));
        UserSubject userSubject = new UserSubject(TestConstants.TEST_TENANT_ID_1,
                101001L, "test", "secret", authorities);

        {
            MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/test/path1");
            request.setServletPath("/api/test/path1");
            boolean actual = strategy.check(userSubject, request);

            Assert.assertTrue(actual);
        }
    }

    @Test
    public void testCheckGrant() {
        RbacAccessDecisionStrategy strategy = createTestObject();

        Collection<GrantedAuthority> authorities = Arrays.asList(
                new RoleAuthority(100001L, "ROLE1"),
                new RoleAuthority(100002L, "ROLE1"));
        UserSubject userSubject = new UserSubject(TestConstants.TEST_TENANT_ID_1,
                101001L, "test", "secret", authorities);

        // 用户101001有资源1的权限
        {
            SecResourceBaseInfoDTO resource = createResourceBaseInfo(100001L);
            boolean actual = strategy.checkGrant(userSubject, resource);

            Assert.assertTrue(actual);
        }

        // 用户101001没有资源8的权限
        {
            SecResourceBaseInfoDTO resource = createResourceBaseInfo(100008L);
            boolean actual = strategy.checkGrant(userSubject, resource);

            Assert.assertFalse(actual);
        }
    }

    /**
     * 创建测试对象
     *
     * @return
     */
    private RbacAccessDecisionStrategy createTestObject() {
        SecResourceDAO resourceDAO = new SecResourceDAO(resourceMapper);
        SecFunctionDAO functionDAO = new SecFunctionDAO(functionMapper);
        DefaultRbacQueryService rbacQueryService = new DefaultRbacQueryService(functionDAO, resourceDAO);
        MockUrlPatternMatcher urlPatternMatcher = new MockUrlPatternMatcher();
        RbacAccessDecisionStrategy strategy = new RbacAccessDecisionStrategy(rbacQueryService, urlPatternMatcher);

        return strategy;
    }

    /**
     * 创建资源信息
     *
     * @param resourceId
     * @return
     */
    private SecResourceBaseInfoDTO createResourceBaseInfo(long resourceId) {
        SecResource resource = resourceMapper.selectById(resourceId);
        SecResourceBaseInfoDTO resourceBaseInfo = SecurityResourceUtil
                .toResourceBaseInfoDTO(resource);

        return resourceBaseInfo;
    }

}
