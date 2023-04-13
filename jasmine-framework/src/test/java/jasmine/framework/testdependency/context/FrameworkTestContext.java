package jasmine.framework.testdependency.context;

import jasmine.framework.context.CurrentSubject;
import jasmine.framework.common.util.I18nUtil;
import jasmine.framework.testdependency.mock.MockLocaleMessageProvider;
import jasmine.framework.testdependency.mock.MockSubjectProvider;
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

    static {
        CurrentSubject.initUtil(new MockSubjectProvider());
        I18nUtil.initUtil(new MockLocaleMessageProvider());
    }

    @Before
    public void initOrResetBeforeTest() {
        CurrentSubject.setSubject(1L, 1L);
    }

}
