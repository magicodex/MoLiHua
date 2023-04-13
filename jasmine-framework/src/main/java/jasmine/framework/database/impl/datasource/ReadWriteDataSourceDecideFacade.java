package jasmine.framework.database.impl.datasource;

import jasmine.framework.common.util.ErrorUtil;
import jasmine.framework.common.util.function.FunctionWithResult;
import jasmine.framework.database.datasource.DataSourceContext;
import jasmine.framework.database.datasource.DataSourceDecideFacade;
import jasmine.framework.database.datasource.DataSourceContextHolder;

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
            throw ErrorUtil.sneakyError(e);
        } finally {
            context.setDataSourceKey(null);
        }
    }

}
