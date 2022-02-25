package jasmine.framework.concurrent;

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
public interface AsyncTaskProvider {

    /**
     * 异步执行
     *
     * @param task
     */
    void async(Runnable task);

    /**
     * 异步执行
     *
     * @param tasks
     * @param <T>
     * @return
     */
    <T> List<T> asyncAndGet(Collection<Callable> tasks);

}
