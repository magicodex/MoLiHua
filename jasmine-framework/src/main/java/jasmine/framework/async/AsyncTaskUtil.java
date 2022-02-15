package jasmine.framework.async;

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
 * @author mh.z
 */
public class AsyncTaskUtil {
    private static ExecutorService EXECUTOR_SERVICE;

    static {
        EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);
    }

    /**
     * 执行
     *
     * @param callables
     * @param <T>
     * @return
     */
    public static <T> List<T> execute(Collection<Callable> callables) {
        QCheckUtil.notNull(callables, "callables null");
        List<T> resultList = new ArrayList<>();

        CompletionService<T> completionService = new ExecutorCompletionService<>(EXECUTOR_SERVICE);
        callables.forEach((callable) -> {
            completionService.submit(callable);
        });

        try {
            for (int i = 0; i < callables.size(); i++) {
                T result = completionService.take().get();
                resultList.add(result);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return resultList;
    }

}
