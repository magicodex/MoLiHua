package jasmine.core.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class QNewUtilTest {

    @Test
    public void testMap1() {
        Assert.assertTrue(QNewUtil.map().isEmpty());
    }

    @Test
    public void testMap2() {
        Assert.assertTrue(QNewUtil.map(10).isEmpty());
    }

    @Test
    public void testList1() {
        Assert.assertTrue(QNewUtil.list().isEmpty());
    }

    @Test
    public void testList2() {
        Assert.assertTrue(QNewUtil.list(10).isEmpty());
    }

}
