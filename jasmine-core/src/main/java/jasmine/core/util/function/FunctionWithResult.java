package jasmine.core.util.function;

/**
 * <p>
 * 返回结果。
 * </p>
 *
 * @author mh.z
 */
public interface FunctionWithResult<T> {

    /**
     * 调用
     *
     * @return
     * @throws Throwable
     */
    T call() throws Throwable;
}
