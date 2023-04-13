package jasmine.framework.database.testdependency;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.ErrorUtil;
import jasmine.framework.common.util.function.FunctionWithResult;
import jasmine.framework.database.datasource.DataSourceDecideFacade;

/**
 * @author mh.z
 */
public class MockDataSourceDecideFacade implements DataSourceDecideFacade {
    private FunctionWithResult<?> lastAction;
    private Object lastResult;

    public FunctionWithResult<?> getLastAction() {
        return lastAction;
    }

    public Object getLastResult() {
        return lastResult;
    }

    @Override
    public <T> T readOny(FunctionWithResult<T> action) {
        CheckUtil.notNull(action, "action null");
        lastAction = action;

        try {
            lastResult = action.call();

            return (T) lastResult;
        } catch (Throwable throwable) {
            throw ErrorUtil.sneakyError(throwable);
        }
    }

}
