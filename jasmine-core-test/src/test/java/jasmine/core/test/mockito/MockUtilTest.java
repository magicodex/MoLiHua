package jasmine.core.test.mockito;

import jasmine.core.test.testdependency.Example1;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author mh.z
 */
public class MockUtilTest {

    @Test
    public void testMock() {
        Example1 example = MockUtil.mock(Example1.class, (target) -> {
            Mockito.when(target.getAttribute1()).thenReturn("zero");
        });

        Assert.assertEquals("zero", example.getAttribute1());
        // 调用未 mock 的方法会报错
        Assert.assertThrows(UnsupportedOperationException.class, () -> {
            example.getAttribute2();
        });
    }

}
