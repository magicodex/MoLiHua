package jasmine.framework.persistence.datasource;

/**
 * @author mh.z
 */
public interface DataActionWithResult {

    /**
     * 调用
     *
     * @param <T>
     * @return
     */
    <T> T call();
}
