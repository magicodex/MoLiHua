package jasmine.framework.test.util;

import jasmine.test.testdependency.Example1;
import jasmine.test.testdependency.Example2;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author mh.z
 */
public class MybatisPlusUtilTest {

    @Test
    public void testGetIdField() {
        {
            Field field = MybatisPlusUtil.getIdField(Example1.class);

            Assert.assertNotNull(field);
            Assert.assertEquals("id", field.getName());
        }

        {
            Field field = MybatisPlusUtil.getIdField(Example2.class);

            Assert.assertNotNull(field);
            Assert.assertEquals("key", field.getName());
        }
    }

}
