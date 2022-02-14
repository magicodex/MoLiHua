package jasmine.framework.persistence.datasource;

/**
 * @author mh.z
 */
public interface DataSourceDecideFacade {

    /**
     * 切换数据源
     *
     * @param category
     * @param key
     * @param action
     */
    void decide(String category, Object key, DataActionWithResult action);

    /**
     * 切换数据源
     *
     * @param category
     * @param key
     * @param action
     */
    <T> T decide(String category, Object key, DataActionWithoutResult action);
}
