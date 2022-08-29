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

        {
            long totalTime = timer.stop();
            Assert.assertTrue(totalTime >= 0);
        }

        {
            timer.restart();
            Assert.assertTrue(timer.getStartTime() > 0);
            Assert.assertTrue(timer.getEndTime() < 0);
            Assert.assertTrue(timer.getTotalTime() < 0);
        }

        {
            long totalTime = timer.stopAndRestart();
            Assert.assertTrue(totalTime >= 0);
            Assert.assertTrue(timer.getEndTime() < 0);
        }
    }

}
