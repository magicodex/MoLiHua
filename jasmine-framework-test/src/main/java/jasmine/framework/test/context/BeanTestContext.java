package jasmine.framework.test.context;

import jasmine.mock.framework.cache.RedisMockUtil;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 支持 Spring 应用测试。
 * </p>
 *
 * @author mh.z
 */
@Transactional(rollbackFor = Exception.class)
@ContextConfiguration(locations = "classpath:/jasmine/framework/test/config/springContext.xml")
public class BeanTestContext {

    @Before
    public void initOrResetBeforeTest() {
        RedisMockUtil.resetRedis();
    }

}
