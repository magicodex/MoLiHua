package jasmine.framework.persistence.testpendency;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QErrorUtil;
import jasmine.core.util.function.FunctionWithResult;
import jasmine.framework.persistence.datasource.DataSourceDecideFacade;

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
        QCheckUtil.notNull(action, "action null");
        lastAction = action;

        try {
            lastResult = action.call();

            return (T) lastResult;
        } catch (Throwable throwable) {
            throw QErrorUtil.sneakyError(throwable);
        }
    }

}
