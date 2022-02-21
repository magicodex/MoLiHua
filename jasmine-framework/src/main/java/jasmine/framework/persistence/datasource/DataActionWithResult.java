package jasmine.framework.persistence.datasource;

/**
 * @author mh.z
 */
public interface DataActionWithResult<T> {

    /**
     * 调用
     *
     * @param <T>
     * @return
     */
    T call() throws Throwable;
}
