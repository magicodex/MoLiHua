package jasmine.mock.concurrent;

import jasmine.core.util.QCollectionUtil;
import jasmine.core.util.QErrorUtil;
import jasmine.framework.concurrent.AsyncTaskProvider;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author mh.z
 */
public class MockAsyncTaskProvider implements AsyncTaskProvider {


    @Override
    public void async(Runnable task) {
        task.run();
    }

    @Override
    public <T> List<T> asyncAndGet(Collection<Callable> tasks) {
        List<Object> resultList = QCollectionUtil.forEach(tasks, (task) -> {
            try {
                return task.call();
            } catch (Exception e) {
                throw QErrorUtil.sneakyError(e);
            }
        });

        return (List<T>) resultList;
    }

}
