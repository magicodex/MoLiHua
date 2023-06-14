package jasmine.framework.common.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @author mh.z
 */
public class NewUtilTest {

    @Test
    public void testMap1() {
        Assert.assertTrue(NewUtil.map().isEmpty());
    }

    @Test
    public void testMap2() {
        Assert.assertTrue(NewUtil.map(10).isEmpty());
    }

    @Test
    public void asMap() {
        {
            Map map = NewUtil.asMap();
            Assert.assertEquals(0, map.size());
        }

        {
            Map map = NewUtil.asMap("key1");
            Assert.assertEquals(1, map.size());
            Assert.assertEquals(null, map.get("key1"));
        }

        {
            Map map = NewUtil.asMap("key1", "value1");
            Assert.assertEquals(1, map.size());
            Assert.assertEquals("value1", map.get("key1"));
        }

        {
            Map map = NewUtil.asMap("key1", "value1", "key2");
            Assert.assertEquals(2, map.size());
            Assert.assertEquals("value1", map.get("key1"));
            Assert.assertEquals(null, map.get("key2"));
        }

        {
            Map map = NewUtil.asMap("key1", "value1", "key2", "value2");
            Assert.assertEquals(2, map.size());
            Assert.assertEquals("value1", map.get("key1"));
            Assert.assertEquals("value2", map.get("key2"));
        }
    }

    @Test
    public void testList1() {
        Assert.assertTrue(NewUtil.list().isEmpty());
    }

    @Test
    public void testList2() {
        Assert.assertTrue(NewUtil.list(10).isEmpty());
    }

}
