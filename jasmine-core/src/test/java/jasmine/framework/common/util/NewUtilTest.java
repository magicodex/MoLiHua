package jasmine.framework.common.util;

import org.junit.Assert;
import org.junit.Test;

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
    public void testList1() {
        Assert.assertTrue(NewUtil.list().isEmpty());
    }

    @Test
    public void testList2() {
        Assert.assertTrue(NewUtil.list(10).isEmpty());
    }

}
