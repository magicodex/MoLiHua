package jasmine.security.authorization;

import jasmine.framework.test.context.AppTestContext;
import jasmine.security.rbac.dao.SecFunctionDAO;
import jasmine.security.rbac.dao.SecResourceDAO;
import jasmine.security.rbac.mapper.SecFunctionMapper;
import jasmine.security.rbac.mapper.SecResourceMapper;
import jasmine.security.strategy.DefaultRbacQueryService;
import jasmine.security.strategy.RbacAccessDecisionStrategy;
import jasmine.security.subject.UserSubject;
import jasmine.core.test.constant.TestConstants;
import jasmine.testconfigure.security.MockUrlPatternMatcher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @author mh.z
 */
@RunWith(SpringRunner.class)
public class DynamicAccessDecisionVoterTest extends AppTestContext {
    @Autowired
    private SecResourceMapper resourceMapper;
    @Autowired
    private SecFunctionMapper functionMapper;

    @Test
    public void testVote() {
        Collection<GrantedAuthority> authorities = Arrays.asList(
                new RoleAuthority(100001L, "ROLE1"),
                new RoleAuthority(100002L, "ROLE1"));
        UserSubject userSubject = new UserSubject(TestConstants.TEST_TENANT_ID_1,
                101001L, "test", "secret", authorities);

        DynamicAccessDecisionVoter voter = createTestObject();
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSubject, null);

        {
            FilterInvocation invocation = new FilterInvocation("",
                    "/api/test/path1", "GET");
            int actual = voter.vote(authentication, invocation, Collections.emptyList());

            Assert.assertEquals(AccessDecisionVoter.ACCESS_GRANTED, actual);
        }
    }

    /**
     * 创建测试对象
     *
     * @return
     */
    private DynamicAccessDecisionVoter createTestObject() {
        SecResourceDAO resourceDAO = new SecResourceDAO(resourceMapper);
        SecFunctionDAO functionDAO = new SecFunctionDAO(functionMapper);

        DefaultRbacQueryService rbacQueryService = new DefaultRbacQueryService(functionDAO, resourceDAO);
        MockUrlPatternMatcher urlPatternMatcher = new MockUrlPatternMatcher();
        RbacAccessDecisionStrategy strategy = new RbacAccessDecisionStrategy(rbacQueryService, urlPatternMatcher);
        DynamicAccessDecisionVoter voter = new DynamicAccessDecisionVoter(strategy);

        return voter;
    }

}
