package jasmine.framework.persistence.datasource;

import jasmine.framework.common.function.FunctionWithResult;

/**
 * @author mh.z
 */
public interface DataSourceDecideFacade {

    /**
     * 只读数据源
     *
     * @param action
     * @param <T>
     * @return
     */
    <T> T readOny(FunctionWithResult<T> action);
}
