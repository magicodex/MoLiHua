package jasmine.core.util;

import jasmine.core.context.RuntimeProvider;

/**
 * <p>
 * Spring工具类。
 * </p>
 *
 * @author mh.z
 */
public class QSpringUtil {
    private static RuntimeProvider runtimeProvider;

    public static void initUtil(RuntimeProvider runtimeProvider) {
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
