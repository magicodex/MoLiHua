package jasmine.framework.persistence.datasource.context;

/**
 * @author mh.z
 */
public class DefaultDataSourceContext implements DataSourceContext {
    /** 数据源key */
    private String dataSourceKey;

    public DefaultDataSourceContext() {
        //
    }

    public DefaultDataSourceContext(String dataSourceKey) {
        this.dataSourceKey = dataSourceKey;
    }

    @Override
    public String getDataSourceKey() {
        return dataSourceKey;
    }

    @Override
    public void setDataSourceKey(String dataSourceKey) {
        this.dataSourceKey = dataSourceKey;
    }

}
