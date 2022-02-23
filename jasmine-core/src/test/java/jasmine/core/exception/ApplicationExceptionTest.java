package jasmine.core.exception;

import jasmine.core.testdependency.MockLocaleMessageProvider;
import jasmine.core.util.QI18nUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class ApplicationExceptionTest {

    @Test
    public void testBuildErrorMessage() {
        MockLocaleMessageProvider provider = new MockLocaleMessageProvider();
        QI18nUtil.initUtil(provider);
        provider.addMessage("message1", "%s + %s = %s");
        provider.addMessage("message2", "%s - %s = %s");

        Assert.assertEquals("15 + 35 = 50", ApplicationException.buildErrorMessage(
                "%s + %s = %s", new Object[]{"15", "35", "50"}));
        Assert.assertEquals("15 + 35 = 50", ApplicationException.buildErrorMessage(
                "$message1", new Object[]{"15", "35", "50"}));
    }

}
