package jasmine.core.util;

import jasmine.core.testdependency.Example1;
import jasmine.core.testdependency.Example2;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mh.z
 */
public class QMapperUtilTest {

    @Test
    public void testMapTo() {
        {
            Example1 source = new Example1();
            source.setInteger1(1);
            source.setString1("Y");
            source.setBoolean1(true);

            Example2 actual = QMapperUtil.mapTo(source, Example2.class);
            Assert.assertEquals(Integer.valueOf(1), actual.getInteger1());
            Assert.assertEquals(true, actual.getBoolean1());
        }

        {
            Example2 source = new Example2();
            source.setInteger1(1);
            source.setBoolean1(true);

            Example1 actual = QMapperUtil.mapTo(source, Example1.class);
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

        List<Example2> actualList = QMapperUtil.mapToList(sourceList, Example2.class);
        Assert.assertEquals(2, actualList.size());

        Assert.assertEquals(Integer.valueOf(1), actualList.get(0).getInteger1());
        Assert.assertEquals(true, actualList.get(0).getBoolean1());
        Assert.assertEquals(Integer.valueOf(0), actualList.get(1).getInteger1());
        Assert.assertEquals(false, actualList.get(1).getBoolean1());
    }

}
