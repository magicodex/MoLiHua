package jasmine.framework.common.util;

import cn.hutool.core.lang.Pair;
import jasmine.framework.testdependency.pojo.Example1;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author mh.z
 */
public class SimpleConvertUtilTest {

    @Test
    public void testSerialize() {
        // 字符串
        {
            byte[] actual = SimpleConvertUtil.serialize("value1");

            Assert.assertNotNull(actual);
        }

        // 对象
        {
            Pair<String, String> pair = new Pair<>("key1", "value1");
            byte[] actual = SimpleConvertUtil.serialize(pair);

            Assert.assertNotNull(actual);
        }
    }

    @Test
    public void testDeserialize() {
        // 字符串
        {
            byte[] bytes = StringUtil.bytes("value1");
            String actual = SimpleConvertUtil.deserialize(bytes, String.class);

            Assert.assertEquals("value1", actual);
        }

        // 对象
        {
            String jsonText = "{\"string1\":\"value1\"}";
            byte[] bytes = StringUtil.bytes(jsonText);
            Example1 actual = SimpleConvertUtil.deserialize(bytes, Example1.class);

            Assert.assertNotNull(actual);
            Assert.assertEquals("value1", actual.getString1());
        }
    }

    @Test
    public void testDeserializeToList() {
        String jsonText = "[\"value1\",\"value2\"]";
        byte[] bytes = StringUtil.bytes(jsonText);
        List<String> actualList = SimpleConvertUtil.deserializeToList(bytes, String.class);

        Assert.assertNotNull(actualList);
        Assert.assertEquals(2, actualList.size());
        Assert.assertEquals("value1", actualList.get(0));
        Assert.assertEquals("value2", actualList.get(1));
    }

}
