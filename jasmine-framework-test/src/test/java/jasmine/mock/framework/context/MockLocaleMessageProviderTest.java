package jasmine.mock.framework.context;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class MockLocaleMessageProviderTest {

    @Test
    public void test() {
        MockLocaleMessageProvider provider = new MockLocaleMessageProvider();
        provider.addMessage("helloWho", "Hello, %s!");

        Assert.assertEquals("Hello, world!", provider.getMessage("helloWho", "world"));
        Assert.assertEquals("Hello, world!", provider.getMessage("$helloWho", "world"));
    }

}
