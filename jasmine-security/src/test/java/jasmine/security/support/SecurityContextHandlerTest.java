package jasmine.security.support;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;

/**
 * @author mh.z
 */
public class SecurityContextHandlerTest {
    private Authentication previousAuthentication;

    @Before
    public void setUp() {
        previousAuthentication = SecurityContextUtil.getCurrentAuthentication();
    }

    @After
    public void tearDown() {
        SecurityContextUtil.setCurrentAuthentication(previousAuthentication);
    }

    @Test
    public void test() {
        // 初始当前安全上下文的认证信息
        TestingAuthenticationToken testAuthentication = new TestingAuthenticationToken(
                "test", "secret");
        SecurityContextUtil.setCurrentAuthentication(testAuthentication);

        // 复制当前安全上下文的信息
        SecurityContextHandler handler = new SecurityContextHandler();
        SecurityContextSnapshot snapshot = (SecurityContextSnapshot) handler.copy();

        // 更改当前安全上下文的认证信息
        SecurityContextUtil.setCurrentAuthentication(null);
        Assert.assertEquals(null, SecurityContextUtil.getCurrentAuthentication());

        // 恢复成复制之前的安全上下文的信息
        snapshot.copyToCurrentThread();
        Assert.assertSame(testAuthentication, SecurityContextUtil.getCurrentAuthentication());
    }

}
