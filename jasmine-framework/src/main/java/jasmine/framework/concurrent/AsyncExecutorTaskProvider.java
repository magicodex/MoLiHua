package jasmine.framework.concurrent;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QErrorUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;

/**
 * <p>
 * 异步工具类。
 * </p>
 *
 * @author mh.z
 */
public class AsyncExecutorTaskProvider implements AsyncTaskProvider {
    private Executor executor;

    public AsyncExecutorTaskProvider(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void async(Runnable task) {
        QCheckUtil.notNull(task, "task null");
        QCheckUtil.notNullProp(executor, "executor null");

        // 开线程执行
        executor.execute(task);
    }

    @Override
    public <T> List<T> asyncAndGet(Collection<Callable> tasks) {
        QCheckUtil.notNull(tasks, "tasks null");
        QCheckUtil.notNullProp(executor, "executor null");
        List<T> resultList = new ArrayList<>();

        // 开线程执行
        CompletionService<T> completionService = new ExecutorCompletionService<>(executor);
        tasks.forEach((task) -> {
            completionService.submit(task);
        });

        // 获取执行结果
        try {
            for (int i = 0; i < tasks.size(); i++) {
                T result = completionService.take().get();
                resultList.add(result);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw QErrorUtil.sneakyError(e);
        }

        return resultList;
    }

}
