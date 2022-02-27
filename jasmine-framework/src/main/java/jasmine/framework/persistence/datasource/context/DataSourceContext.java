package jasmine.framework.persistence.datasource.context;

/**
 * @author mh.z
 */
public interface DataSourceContext {

    /**
     * 返回数据源key
     *
     * @return
     */
    String getDataSourceKey();

    /**
     * 设置数据源key
     *
     * @param dataSourceKey
     */
    void setDataSourceKey(String dataSourceKey);
}
