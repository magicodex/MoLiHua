package jasmine.framework.i18n;

import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

/**
 * @author mh.z
 */
public class DeclareI18nScanUtilTest {
    private static final String SCAN_PATH = "classpath:/jasmine/framework/i18n/testdependency/TestMessageConstants.class";

    @Test
    public void testScan() {
        Properties properties = DeclareI18nScanUtil.scan(SCAN_PATH);

        Assert.assertEquals("message 1", properties.get("message1"));
        Assert.assertEquals("message 2", properties.get("message2"));
    }

}
