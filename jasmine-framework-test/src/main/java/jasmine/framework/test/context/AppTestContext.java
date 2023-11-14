package jasmine.framework.test.context;

import jasmine.framework.context.CurrentSubject;
import jasmine.framework.test.constant.TestConstants;
import jasmine.mock.framework.cache.RedisMockUtil;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 支持 Spring 应用测试（提供租户相关支持）。
 * </p>
 *
 * @author mh.z
 */
@Transactional(rollbackFor = Exception.class)
@ContextConfiguration(locations = "classpath:/jasmine/framework/test/config/springContext.xml")
public class AppTestContext {

    @Before
    public void initOrResetBeforeTest() {
        RedisMockUtil.resetRedis();

        CurrentSubject.setSubject(TestConstants.TEST_TENANT_ID_1, TestConstants.TEST_USER_ID_UNKNOWN);
    }

}
