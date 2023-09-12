package jasmine.framework.common.util;

import jasmine.framework.common.exception.InvalidPropertyException;
import jasmine.framework.context.RuntimeProvider;
import jasmine.framework.context.WithContext;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

import javax.annotation.Nonnull;

/**
 * <p>
 * Spring工具类。
 * </p>
 *
 * @author mh.z
 */
public class SpringUtil implements WithContext {
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
            throw new InvalidPropertyException("SpringUtil.runtimeProvider null", null);
        }

        return (T) runtimeProvider.getByName(name);
    }

    /**
     * 查找 bean 对象
     *
     * @param name
     * @param required
     * @return
     * @param <T>
     */
    public static <T> T getBean(@Nonnull String name, boolean required) {
        if (runtimeProvider == null) {
            throw new InvalidPropertyException("SpringUtil.runtimeProvider null", null);
        }

        return (T) runtimeProvider.getByName(name, required);
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
            throw new InvalidPropertyException("SpringUtil.runtimeProvider null", null);
        }

        return runtimeProvider.getByType(type);
    }

    /**
     * 查找 bean 对象
     *
     * @param type
     * @param required
     * @return
     * @param <T>
     */
    public static <T> T getBean(@Nonnull Class<T> type, boolean required) {
        if (runtimeProvider == null) {
            throw new InvalidPropertyException("SpringUtil.runtimeProvider null", null);
        }

        return runtimeProvider.getByType(type, required);
    }

    /**
     * 查找 bean 对象
     *
     * @param applicationContext
     * @param name
     * @param required
     * @return
     * @param <T>
     */
    public static <T> T getBean(@Nonnull ApplicationContext applicationContext,
                                @Nonnull String name, boolean required) {
        T bean = null;

        if (required) {
            bean = (T) applicationContext.getBean(name);
        } else {
            try {
                bean = (T) applicationContext.getBean(name);
            } catch (NoSuchBeanDefinitionException e) {
                //
            }
        }

        return bean;
    }

    /**
     * 查找 bean 对象
     *
     * @param applicationContext
     * @param type
     * @param required
     * @return
     * @param <T>
     */
    public static <T> T getBean(@Nonnull ApplicationContext applicationContext,
                                @Nonnull Class<T> type, boolean required) {
        T bean = null;

        if (required) {
            bean = applicationContext.getBean(type);
        } else {
            try {
                bean = applicationContext.getBean(type);
            } catch (NoSuchBeanDefinitionException e) {
                //
            }
        }

        return bean;
    }

}
