package jasmine.framework.concurrent.testdependency;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.CollectionUtil;
import jasmine.framework.common.util.ErrorUtil;
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
        CheckUtil.notNull(task, "task null");

        task.run();
    }

    @Override
    public <T> List<T> asyncAndGet(Collection<Callable> tasks) {
        CheckUtil.notNull(tasks, "tasks null");

        List<T> resultList = (List<T>) CollectionUtil.mapToList(tasks, (callable) -> {
            try {
                return callable.call();
            } catch (Exception e) {
                throw ErrorUtil.sneakyError(e);
            }
        });

        return resultList;
    }

}
