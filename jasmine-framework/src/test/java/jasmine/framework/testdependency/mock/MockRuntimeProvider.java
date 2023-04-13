package jasmine.framework.testdependency.mock;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.CheckUtil;

import java.util.Map;

/**
 * @author mh.z
 */
public class MockRuntimeProvider implements RuntimeProvider {
    private RuntimeProvider runtimeProvider;
    private Map<String, Object> objects;

    public MockRuntimeProvider(RuntimeProvider runtimeProvider,
                               Map<String, Object> objects) {
        this.runtimeProvider = runtimeProvider;
        this.objects = objects;
    }

    @Override
    public <T> T getByType(Class<T> type) {
        CheckUtil.notNull(type, "type null");

        return getByName(type.getName());
    }

    @Override
    public <T> T getByType(Class<T> type, boolean required) {
        CheckUtil.notNull(type, "type null");

        return getByName(type.getName(), required);
    }

    @Override
    public <T> T getByName(String name) {
        return getByName(name, true);
    }

    @Override
    public <T> T getByName(String name, boolean required) {
        Object object = objects.get(name);

        if (object == null) {
            if (runtimeProvider != null) {
                object = runtimeProvider.getByName(name, required);
            }
        }

        return (T) object;
    }

}
