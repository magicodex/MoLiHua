package jasmine.framework.common.exception;

import jasmine.framework.testdependency.MockLocaleMessageProvider;
import jasmine.framework.common.util.I18nUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class ApplicationExceptionTest {

    @Test
    public void testBuildErrorMessage() {
        MockLocaleMessageProvider provider = new MockLocaleMessageProvider();
        I18nUtil.initUtil(provider);
        provider.addMessage("message1", "{0} + {1} = {2}");
        provider.addMessage("message2", "{0} + {1} = {2}");

        Assert.assertEquals("15 + 35 = 50", ApplicationException.buildErrorMessage(
                "{0} + {1} = {2}", new Object[]{"15", "35", "50"}));
        Assert.assertEquals("15 + 35 = 50", ApplicationException.buildErrorMessage(
                "$message1", new Object[]{"15", "35", "50"}));
    }

}
