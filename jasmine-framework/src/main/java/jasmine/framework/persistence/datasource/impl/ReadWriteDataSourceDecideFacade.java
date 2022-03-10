package jasmine.framework.persistence.datasource.impl;

import jasmine.framework.common.function.FunctionWithResult;
import jasmine.framework.persistence.datasource.DataSourceDecideFacade;
import jasmine.framework.persistence.datasource.context.DataSourceContext;
import jasmine.framework.persistence.datasource.context.DataSourceContextHolder;

/**
 * @author mh.z
 */
public class ReadWriteDataSourceDecideFacade implements DataSourceDecideFacade {
    private static final String READ_DATA_SOURCE_KEY = "read";

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
