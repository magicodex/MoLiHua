package jasmine.framework.concurrent;

import jasmine.framework.common.util.CheckUtil;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>
 * 异步工具类。
 * </p>
 *
 * @author mh.z
 */
public class AsyncTaskUtil {
    private static AsyncTaskProvider provider;

    public static void initUtil(AsyncTaskProvider provider) {
        AsyncTaskUtil.provider = provider;
    }

    public static AsyncTaskProvider getProvider() {
        return provider;
    }

    /**
     * 异步执行
     *
     * @param task
     */
    public static void async(Runnable task) {
        CheckUtil.notNull(task, "task null");
        CheckUtil.notNullProp(provider, "provider null");

        provider.async(task);
    }

    /**
     * 异步执行
     *
     * @param tasks
     * @param <T>
     * @return
     */
    public static <T> List<T> asyncAndGet(Collection<Callable> tasks) {
        CheckUtil.notNull(tasks, "tasks null");
        CheckUtil.notNullProp(provider, "provider null");

        return provider.asyncAndGet(tasks);
    }

}
