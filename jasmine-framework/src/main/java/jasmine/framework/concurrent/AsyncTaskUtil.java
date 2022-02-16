package jasmine.framework.concurrent;

import jasmine.core.util.QCheckUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * 异步工具类。
 * </p>
 *
 * @author mh.z
 */
public class AsyncTaskUtil {
    private static ExecutorService EXECUTOR_SERVICE;

    static {
        EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);
    }

    /**
     * 异步执行
     *
     * @param task
     */
    public void async(Runnable task) {
        QCheckUtil.notNull(task, "task null");

        // 开线程执行
        EXECUTOR_SERVICE.submit(task);
    }

    /**
     * 异步执行
     *
     * @param tasks
     * @param <T>
     * @return
     */
    public static <T> List<T> asyncAndGet(Collection<Callable> tasks) {
        QCheckUtil.notNull(tasks, "tasks null");
        List<T> resultList = new ArrayList<>();

        // 开线程执行
        CompletionService<T> completionService = new ExecutorCompletionService<>(EXECUTOR_SERVICE);
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
            throw new RuntimeException(e);
        }

        return resultList;
    }

}
