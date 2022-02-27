package jasmine.framework.persistence.datasource.impl;

import jasmine.framework.persistence.datasource.DataActionWithResult;
import jasmine.framework.persistence.datasource.DataActionWithoutResult;
import jasmine.framework.persistence.datasource.DataSourceDecideFacade;
import jasmine.framework.persistence.datasource.context.DataSourceContext;
import jasmine.framework.persistence.datasource.context.DataSourceContextHolder;

/**
 * @author mh.z
 */
public class ReadWriteDataSourceDecideFacade implements DataSourceDecideFacade {
    private static final String READ_DATA_SOURCE_KEY = "read";

    @Override
    public void decide(String category, Object key, DataActionWithoutResult action) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T decide(String category, Object key, DataActionWithResult<T> action) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T readOny(DataActionWithResult<T> action) {
        DataSourceContext context = DataSourceContextHolder.getContext();

        try {
            context.setDataSourceKey(READ_DATA_SOURCE_KEY);
            
            return action.call();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            context.setDataSourceKey(null);
        }
    }

}
