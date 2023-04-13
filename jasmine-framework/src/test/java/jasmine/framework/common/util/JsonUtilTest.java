package jasmine.framework.common.util;

import jasmine.framework.testdependency.pojo.Example1;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.List;

/**
 * @author mh.z
 */
public class JsonUtilTest {

    @Test
    public void testToJson() throws JSONException {
        Example1 example = new Example1();
        example.setString1("1");
        example.setBoolean1(false);

        String expected = "{\"string1\":\"1\",\"boolean1\":false}";
        String actual = JsonUtil.toJson(example);
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    public void testFromJson() {
        String json = "{\"string1\":\"1\",\"boolean1\":false}";
        Example1 example = JsonUtil.fromJson(json, Example1.class);

        Assert.assertEquals("1", example.getString1());
        Assert.assertFalse(example.getBoolean1());
    }

    @Test
    public void testFromArray() {
        String json = "[{\"string1\":\"1\",\"boolean1\":true}," +
                "{\"string1\":\"0\",\"boolean1\":false}]";
        List<Example1> actualList = JsonUtil.fromArray(json, Example1.class);

        Assert.assertEquals(2, actualList.size());
        Assert.assertTrue(actualList.get(0).getBoolean1());
        Assert.assertFalse(actualList.get(1).getBoolean1());
    }

}
