package jasmine.core.util.batch;

import jasmine.core.testdependency.Example1;
import jasmine.core.util.CollUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author mh.z
 */
public class BatchMapTest {

    @Test
    public void test() {
        BatchMap<Integer, Example1> exampleMap = new BatchMap<>(Example1::getInteger1, (keys) -> {
            return CollUtil.mapToList(keys, (key) -> {
                return Example1.create(("example" + key), key, null);
            });
        });

        exampleMap.load(Arrays.asList(1, 2, 3));
        Assert.assertEquals(3, exampleMap.size());
        Assert.assertEquals("example1", exampleMap.get(1).getString1());
        Assert.assertEquals("example2", exampleMap.get(2).getString1());
        Assert.assertEquals("example3", exampleMap.get(3).getString1());
    }

}
