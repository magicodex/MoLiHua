package jasmine.framework.remote.consumer;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class MessageReceiveServiceImplTest {

    @Test
    public void testConvertText() {
        ConsumerServiceImpl service = new ConsumerServiceImpl(null);

        // 布尔值
        Assert.assertEquals(true, service.convertText("true", Boolean.class));
        Assert.assertEquals(false, service.convertText("false", Boolean.class));
        Assert.assertEquals(null, service.convertText("", Boolean.class));

        // 字符
        Assert.assertEquals('t', service.convertText("text", Character.class));
        Assert.assertEquals(null, service.convertText("", Character.class));

        // 长整型
        Assert.assertEquals(Long.valueOf(1), service.convertText("1", Long.class));
        Assert.assertEquals(null, service.convertText("", Long.class));
    }

}
