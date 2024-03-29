package jasmine.mock.framework.concurrent;

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

        List<Object> resultList = CollectionUtil.forEach(tasks, (task) -> {
            try {
                return task.call();
            } catch (Exception e) {
                throw ErrorUtil.sneakyError(e);
            }
        });

        return (List<T>) resultList;
    }

}
