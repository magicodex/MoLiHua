package jasmine.framework.mybatis.util;

import jasmine.framework.mybatis.util.MybatisPlusColumnMapping;
import jasmine.framework.testdependency.pojo.Example2;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class MybatisPlusColumnMappingTest {

    @Test
    public void test() {
        MybatisPlusColumnMapping mapping = new MybatisPlusColumnMapping(Example2.class);

        Assert.assertEquals("id", mapping.get("id"));
        Assert.assertEquals("key", mapping.get("key"));
        Assert.assertEquals("attribute1", mapping.get("attribute1"));
        Assert.assertEquals("attribute2", mapping.get("attribute2"));
        Assert.assertEquals("attribute3", mapping.get("attribute3"));
        Assert.assertEquals("attribute4", mapping.get("attribute_4"));
        Assert.assertEquals("attribute5", mapping.get("attribute_5"));
    }

}
