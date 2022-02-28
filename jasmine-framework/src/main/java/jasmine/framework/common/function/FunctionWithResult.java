package jasmine.framework.common.function;

/**
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
