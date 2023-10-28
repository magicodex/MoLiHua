package jasmine.framework.testdependency.context;

import jasmine.framework.common.util.I18nUtil;
import jasmine.framework.context.CurrentSubject;
import jasmine.framework.i18n.builder.MessageSourceBuilder;
import jasmine.framework.i18n.impl.DefaultLocaleMessageProvider;
import jasmine.framework.testdependency.mock.MockSubjectProvider;
import org.junit.Before;
import org.springframework.context.MessageSource;
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

        MessageSourceBuilder messageSourceBuilder = new MessageSourceBuilder();
        MessageSource messageSource = messageSourceBuilder.build();
        DefaultLocaleMessageProvider messageProvider = new DefaultLocaleMessageProvider(messageSource);
        I18nUtil.initUtil(messageProvider);
    }

    @Before
    public void initOrResetBeforeTest() {
        CurrentSubject.setSubject(1L, 1L);
    }

}
