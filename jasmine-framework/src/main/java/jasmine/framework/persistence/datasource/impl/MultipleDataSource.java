package jasmine.framework.persistence.datasource.impl;

import jasmine.framework.persistence.datasource.context.DataSourceContext;
import jasmine.framework.persistence.datasource.context.DataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * @author mh.z
 */
public class MultipleDataSource extends AbstractRoutingDataSource {

    public MultipleDataSource(Map<Object, Object> dataSources) {
        setTargetDataSources(dataSources);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceContext context = DataSourceContextHolder.getContext();

        if (context != null) {
            return context.getDataSourceKey();
        }

        return null;
    }

}
