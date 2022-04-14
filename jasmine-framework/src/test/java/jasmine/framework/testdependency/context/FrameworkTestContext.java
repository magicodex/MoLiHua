package jasmine.framework.testdependency.context;

import jasmine.core.context.CurrentSubject;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 支持框架测试。
 * </p>
 *
 * @author mh.z
 */
@Transactional(rollbackFor = Exception.class)
@ContextConfiguration(locations = "classpath:/test/framework/testdependency/config/springContext.xml")
public class FrameworkTestContext {

    @Before
    public void initOrResetBeforeTest() {
        CurrentSubject.setSubject(1L, null);
    }

}
