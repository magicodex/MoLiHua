package jasmine.core.util.timer;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mh.z
 */
public class ExecuteTimerTest {

    @Test
    public void test() {
        ExecuteTimer timer = ExecuteTimer.start();
        long totalTime = timer.stop();

        Assert.assertTrue(totalTime >= 0);
    }

}
