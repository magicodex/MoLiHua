package jasmine.core.util.batch;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author mh.z
 */
public class BatchCallUtilTest {

    @Test
    public void test() {
        List<Long> list = Arrays.asList(1L, 2L, 3L, 4L, 5L,
                6L, 7L, 8L, 9L, 10L);
        List<List<Long>> actualList = new ArrayList<>();

        BatchCallUtil.call(list, 4, (part) -> {
            actualList.add(part);
        });

        Assert.assertEquals(3, actualList.size());
        //
        Assert.assertEquals(4, actualList.get(0).size());
        Assert.assertEquals(Arrays.asList(1L, 2L, 3L, 4L), actualList.get(0));
        //
        Assert.assertEquals(4, actualList.get(1).size());
        Assert.assertEquals(Arrays.asList(5L, 6L, 7L, 8L), actualList.get(1));
        //
        Assert.assertEquals(2, actualList.get(2).size());
        Assert.assertEquals(Arrays.asList(9L, 10L), actualList.get(2));
    }

}
