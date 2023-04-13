package jasmine.framework.common.util;

import jasmine.framework.testdependency.Example1;
import jasmine.framework.testdependency.Example2;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mh.z
 */
public class MapperUtilTest {

    @Test
    public void testMapTo() {
        {
            Example1 source = new Example1();
            source.setInteger1(1);
            source.setString1("Y");
            source.setBoolean1(true);

            Example2 actual = MapperUtil.mapTo(source, Example2.class);
            Assert.assertEquals(Integer.valueOf(1), actual.getInteger1());
            Assert.assertEquals(true, actual.getBoolean1());
        }

        {
            Example2 source = new Example2();
            source.setInteger1(1);
            source.setBoolean1(true);

            Example1 actual = MapperUtil.mapTo(source, Example1.class);
            Assert.assertNull(actual.getString1());
            Assert.assertEquals(Integer.valueOf(1), actual.getInteger1());
            Assert.assertEquals(true, actual.getBoolean1());
        }
    }

    @Test
    public void testMapToList() {
        List<Example1> sourceList = new ArrayList<>();
        sourceList.add(Example1.create("Y", 1, true));
        sourceList.add(Example1.create("N", 0, false));

        List<Example2> actualList = MapperUtil.mapToList(sourceList, Example2.class);
        Assert.assertEquals(2, actualList.size());

        Assert.assertEquals(Integer.valueOf(1), actualList.get(0).getInteger1());
        Assert.assertEquals(true, actualList.get(0).getBoolean1());
        Assert.assertEquals(Integer.valueOf(0), actualList.get(1).getInteger1());
        Assert.assertEquals(false, actualList.get(1).getBoolean1());
    }

    @Test
    public void testMapFields() {
        Example1 example1 = new Example1();
        example1.setString1("value1");
        example1.setBoolean1(false);
        example1.setInteger1(0);

        Example2 example2 = new Example2();
        example2.setBoolean1(true);
        example2.setInteger1(1000);

        MapperUtil.mapFields(example2, example1);
        Assert.assertEquals("value1", example1.getString1());
        Assert.assertEquals(true, example1.getBoolean1());
        Assert.assertEquals(Integer.valueOf(1000), example1.getInteger1());
    }

}
