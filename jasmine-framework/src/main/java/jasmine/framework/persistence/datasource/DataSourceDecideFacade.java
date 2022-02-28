package jasmine.framework.persistence.datasource;

import jasmine.framework.common.function.FunctionWithResult;
import jasmine.framework.common.function.FunctionWithoutResult;

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
    void decide(String category, Object key, FunctionWithoutResult action);

    /**
     * 切换数据源
     *
     * @param category
     * @param key
     * @param action
     */
    <T> T decide(String category, Object key, FunctionWithResult<T> action);

    /**
     * 只读数据源
     *
     * @param action
     * @param <T>
     * @return
     */
    <T> T readOny(FunctionWithResult<T> action);
}
