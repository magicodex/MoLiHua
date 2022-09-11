package jasmine.mock.core.context;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;

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
        QCheckUtil.notNull(type, "type null");

        return getByName(type.getName());
    }

    @Override
    public <T> T getByType(Class<T> type, boolean required) {
        QCheckUtil.notNull(type, "type null");

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
