package jasmine.framework.database.mybatisplus.util;

import jasmine.framework.database.mybatisplus.testdependency.pojo.Example1;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author mh.z
 */
public class MybatisPlusCsvUtilTest {
    private static final String EXAMPLE1_PATH = "/test/framework/database/csv/Example1_1.csv";

    @Test
    public void testReadCSV() throws IOException {
        try (InputStream inputStream = MybatisPlusCsvUtilTest.class.getResourceAsStream(EXAMPLE1_PATH)) {
            List<Example1> actualList = MybatisPlusCsvUtil.readCSV(inputStream, Example1.class);

            Assert.assertEquals(10, actualList.size());
            Assert.assertEquals(Long.valueOf(1), actualList.get(0).getId());
            Assert.assertEquals("zero", actualList.get(0).getAttribute1());
            Assert.assertEquals(Integer.valueOf(0), actualList.get(0).getAttribute2());
            Assert.assertEquals(true, actualList.get(0).getAttribute3());
            Assert.assertEquals(Long.valueOf(10), actualList.get(9).getId());
            Assert.assertEquals("nine", actualList.get(9).getAttribute1());
            Assert.assertEquals(Integer.valueOf(9), actualList.get(9).getAttribute2());
            Assert.assertEquals(false, actualList.get(9).getAttribute3());
        }
    }

}
