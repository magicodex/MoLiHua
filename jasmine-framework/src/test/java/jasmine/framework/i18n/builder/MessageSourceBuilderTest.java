package jasmine.framework.i18n.builder;

import jasmine.framework.i18n.builder.MessageSourceBuilder;
import jasmine.framework.i18n.testdependency.TestMessageConstants;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author mh.z
 */
public class MessageSourceBuilderTest {
    private static final String RESOURCE_PREFIX = "test/framework/i18n/messages";
    private static final String SCAN_PATH = "classpath:/jasmine/framework" +
            "/i18n/testdependency/TestMessageConstants.class";

    @Test
    public void test() {
        MessageSourceBuilder builder = new MessageSourceBuilder();
        builder.setResourcePrefix(RESOURCE_PREFIX);
        builder.setEncoding("UTF-8");
        builder.setConstantPattern(SCAN_PATH);

        MessageSource messageSource = builder.build();
        String actual = messageSource.getMessage(TestMessageConstants.MESSAGE_1,
                new Object[0], Locale.SIMPLIFIED_CHINESE);
        Assert.assertEquals("消息1", actual);
    }

}
