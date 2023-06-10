package jasmine.framework.common.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class ResourceUtilTest {
    private static final String PATH_1 = "classpath:/test/framework/common/ResourceUtilTest_1.txt";

    @Test
    public void test() {
        String actual = ResourceUtil.getStringFromPath(PATH_1);

        Assert.assertEquals("test text", actual);
    }

}
