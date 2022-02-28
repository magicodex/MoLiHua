package jasmine.framework.persistence.datasource.impl;

import jasmine.framework.common.function.FunctionWithResult;
import jasmine.framework.common.function.FunctionWithoutResult;
import jasmine.framework.persistence.datasource.DataSourceDecideFacade;
import jasmine.framework.persistence.datasource.context.DataSourceContext;
import jasmine.framework.persistence.datasource.context.DataSourceContextHolder;

/**
 * @author mh.z
 */
public class ReadWriteDataSourceDecideFacade implements DataSourceDecideFacade {
    private static final String READ_DATA_SOURCE_KEY = "read";

    @Override
    public void decide(String category, Object key, FunctionWithoutResult action) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T decide(String category, Object key, FunctionWithResult<T> action) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T readOny(FunctionWithResult<T> action) {
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
