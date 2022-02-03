package jasmine.core.util;

import jasmine.core.context.RuntimeProvider;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class QSpringUtil {
    private static RuntimeProvider runtimeProvider;

    public QSpringUtil(RuntimeProvider runtimeProvider) {
        QSpringUtil.runtimeProvider = runtimeProvider;
    }

    public static void setRuntimeProvider(RuntimeProvider runtimeProvider) {
        QSpringUtil.runtimeProvider = runtimeProvider;
    }

    /**
     * 查找 bean 对象
     *
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name) {
        return (T) runtimeProvider.getByName(name);
    }

    /**
     * 查找 bean 对象
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> type) {
        return runtimeProvider.getByType(type);
    }

}
