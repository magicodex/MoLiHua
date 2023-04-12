package jasmine.framework.database.mybatisplus.testdependency.context;

import jasmine.core.context.CurrentSubject;
import jasmine.core.util.I18nUtil;
import jasmine.mock.core.context.MockLocaleMessageProvider;
import jasmine.mock.core.context.MockSubjectProvider;
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
@ContextConfiguration(locations = "classpath:/test/mybatis/testdependency/config/springContext.xml")
public class MybatisTestContext {

    static {
        CurrentSubject.initUtil(new MockSubjectProvider());
        I18nUtil.initUtil(new MockLocaleMessageProvider());
    }

    @Before
    public void initOrResetBeforeTest() {
        CurrentSubject.setSubject(1L, 1L);
    }

}
