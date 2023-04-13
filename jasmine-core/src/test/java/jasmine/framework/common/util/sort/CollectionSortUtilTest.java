package jasmine.framework.common.util.sort;

import jasmine.framework.testdependency.Example1;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mh.z
 */
public class CollectionSortUtilTest {

    @Test
    public void test() {
        List<Example1> exampleList = new ArrayList<>();
        exampleList.add(Example1.create("example1", 1002, null));
        exampleList.add(Example1.create("example2", 1001, null));
        exampleList.add(Example1.create("example3", 1002, null));

        List<Example1> actualList = CollectionSortUtil.sort(exampleList,
                SortKey.asc(Example1::getInteger1), SortKey.desc(Example1::getString1));

        Assert.assertEquals("example2", actualList.get(0).getString1());
        Assert.assertEquals("example3", actualList.get(1).getString1());
        Assert.assertEquals("example1", actualList.get(2).getString1());
    }

}
