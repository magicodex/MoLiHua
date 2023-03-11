package jasmine.test.mockito;

import jasmine.test.testdependency.Example1;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author mh.z
 */
public class CustomAnswerTest {

    @Test
    public void test() {
        CustomAnswer answer = new CustomAnswer();
        Example1 example = Mockito.mock(Example1.class, answer);

        answer.mock(example, new CustomMock<Example1>() {
            @Override
            public void mock(Example1 target) {
                Mockito.when(target.getAttribute1()).thenReturn("1");
                Mockito.when(target.getAttribute2()).thenReturn(1);
                Mockito.when(target.getAttribute3()).thenReturn(true);
            }
        });

        Assert.assertEquals("1", example.getAttribute1());
        Assert.assertEquals(Integer.valueOf(1), example.getAttribute2());
        Assert.assertEquals(true, example.getAttribute3());
    }

}
