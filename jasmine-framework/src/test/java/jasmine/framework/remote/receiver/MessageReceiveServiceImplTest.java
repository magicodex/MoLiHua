package jasmine.framework.remote.receiver;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class MessageReceiveServiceImplTest {

    @Test
    public void testConvertText() {
        MessageReceiveServiceImpl service = new MessageReceiveServiceImpl(null);

        // 布尔值
        Assert.assertEquals(true, service.convertText("true", Boolean.class));
    }

}
