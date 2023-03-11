package jasmine.framework.concurrent.impl;

import jasmine.core.util.CheckUtil;
import jasmine.core.util.ErrorUtil;
import jasmine.framework.concurrent.AsyncTaskProvider;

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
        CheckUtil.notNull(task, "task null");
        CheckUtil.notNullProp(executor, "executor null");

        // 开线程执行
        executor.execute(task);
    }

    @Override
    public <T> List<T> asyncAndGet(Collection<Callable> tasks) {
        CheckUtil.notNull(tasks, "tasks null");
        CheckUtil.notNullProp(executor, "executor null");
        List<T> resultList = new ArrayList<>(tasks.size());

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
            throw ErrorUtil.sneakyError(e);
        }

        return resultList;
    }

}
