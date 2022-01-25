package jasmine.framework.context;

import jasmine.framework.util.QSpringUtil;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class SpringRuntimeProvider implements RuntimeProvider {

    @Override
    public <T> T getByType(Class<T> type) {
        return QSpringUtil.getBean(type);
    }

    @Override
    public <T> T getByName(String name) {
        return (T) QSpringUtil.getBean(name);
    }

}
