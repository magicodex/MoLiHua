package jasmine.test.csv;

import jasmine.test.testdependency.Example2;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class MybatisPlusColumnMappingTest {

    @Test
    public void test() {
        MybatisPlusColumnMapping mapping = new MybatisPlusColumnMapping(Example2.class);

        Assert.assertEquals("attribute4", mapping.get("attribute_4"));
        Assert.assertEquals("attribute5", mapping.get("attribute_5"));
    }

}
