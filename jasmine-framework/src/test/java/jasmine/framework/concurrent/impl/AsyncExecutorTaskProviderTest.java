package jasmine.framework.concurrent.impl;

import jasmine.framework.concurrent.impl.AsyncExecutorTaskProvider;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mh.z
 */
public class AsyncExecutorTaskProviderTest {

    @Test
    public void testAsyncAndGet() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        AsyncExecutorTaskProvider asyncProvider = new AsyncExecutorTaskProvider(executorService);

        Collection<Callable> callables = new ArrayList<>();
        callables.add(() -> "value1");
        callables.add(() -> "value2");
        List<String> actualList = asyncProvider.asyncAndGet(callables);

        Assert.assertEquals(2, actualList.size());
        Assert.assertTrue(actualList.contains("value1"));
        Assert.assertTrue(actualList.contains("value2"));
    }

}
