package jasmine.test.util;

import jasmine.test.testdependency.Example1;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author mh.z
 */
public class StreamUtilTest {
    private static final String EXAMPLE1_PATH = "/csv/Example1_1.csv";

    @Test
    public void testReadCSV() throws IOException {
        try (InputStream inputStream = StreamUtilTest.class.getResourceAsStream(EXAMPLE1_PATH)) {
            List<Example1> actualList = StreamUtil.readCSV(inputStream, Example1.class);

            Assert.assertEquals(10, actualList.size());
            Assert.assertEquals("zero", actualList.get(0).getString1());
            Assert.assertEquals(Integer.valueOf(0), actualList.get(0).getInteger1());
            Assert.assertEquals(true, actualList.get(0).getBoolean1());
            Assert.assertEquals("nine", actualList.get(9).getString1());
            Assert.assertEquals(Integer.valueOf(9), actualList.get(9).getInteger1());
            Assert.assertEquals(false, actualList.get(9).getBoolean1());
        }
    }

}
