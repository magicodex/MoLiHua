package jasmine.framework.common.util;

import jasmine.framework.context.RuntimeProvider;
import jasmine.framework.common.exception.InvalidPropertyException;

import javax.annotation.Nonnull;

/**
 * <p>
 * Spring工具类。
 * </p>
 *
 * @author mh.z
 */
public class SpringUtil {
    private static RuntimeProvider runtimeProvider;

    public static void initUtil(RuntimeProvider runtimeProvider) {
        SpringUtil.runtimeProvider = runtimeProvider;
    }

    public static RuntimeProvider getRuntimeProvider() {
        return runtimeProvider;
    }

    /**
     * 查找 bean 对象
     *
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(@Nonnull String name) {
        if (runtimeProvider == null) {
            throw new InvalidPropertyException("QSpringUtil.runtimeProvider null", null);
        }

        return (T) runtimeProvider.getByName(name);
    }

    /**
     * 查找 bean 对象
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getBean(@Nonnull Class<T> type) {
        if (runtimeProvider == null) {
            throw new InvalidPropertyException("QSpringUtil.runtimeProvider null", null);
        }

        return runtimeProvider.getByType(type);
    }

}
