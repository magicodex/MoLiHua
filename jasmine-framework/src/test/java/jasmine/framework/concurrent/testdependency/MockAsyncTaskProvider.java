package jasmine.framework.concurrent.testdependency;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollUtil;
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
        QCheckUtil.notNull(task, "task null");

        task.run();
    }

    @Override
    public <T> List<T> asyncAndGet(Collection<Callable> tasks) {
        QCheckUtil.notNull(tasks, "tasks null");

        List<T> resultList = (List<T>) QCollUtil.mapToList(tasks, (callable) -> {
            try {
                return callable.call();
            } catch (Exception e) {
                throw QErrorUtil.sneakyError(e);
            }
        });

        return resultList;
    }

}
