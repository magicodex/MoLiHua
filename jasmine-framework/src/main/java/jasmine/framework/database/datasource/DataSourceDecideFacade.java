package jasmine.framework.database.datasource;

import jasmine.core.util.function.FunctionWithResult;

/**
 * @author mh.z
 */
public interface DataSourceDecideFacade {

    /**
     * 切换到只读数据源
     *
     * @param action
     * @param <T>
     * @return
     */
    <T> T readOny(FunctionWithResult<T> action);
}
