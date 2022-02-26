package jasmine.framework.persistence.datasource;

/**
 * @author mh.z
 */
public interface DataActionWithResult<T> {

    /**
     * 调用
     *
     * @return
     * @throws Throwable
     */
    T call() throws Throwable;
}
