package jasmine.core.util;

import jasmine.core.exception.InvalidPropertyException;
import jasmine.core.exception.UnexpectedException;

/**
 * <p>
 * 可用于检查参数、返回值等。
 * </p>
 *
 * @author mh.z
 */
public class QCheckUtil {

    /**
     * 期望不为null
     *
     * @param value
     * @param <T>
     * @return
     */
    public static <T> T notNull(T value) {
        if (value == null) {
            throw new UnexpectedException("expected not null, but actual null", null);
        }

        return value;
    }

    /**
     * 期望不为null
     *
     * @param value
     * @param message
     * @param <T>
     * @return
     */
    public static <T> T notNull(T value, String message) {
        if (value == null) {
            throw new UnexpectedException(message, null);
        }

        return value;
    }

    /**
     * 期望不为null
     *
     * @param value
     * @param format 错误信息格式
     * @param args 错误信息参数
     * @param <T>
     * @return
     */
    public static <T> T notNullFmt(T value, String format, Object... args) {
        if (value == null) {
            throw new UnexpectedException(format, args);
        }

        return value;
    }

    /**
     * 期望指定属性的值不为null
     *
     * @param value
     * @param message
     * @param <T>
     * @return
     */
    public static <T> T notNullProp(T value, String message) {
        if (value == null) {
            throw new InvalidPropertyException(message, null);
        }

        return value;
    }

    /**
     * 期望指定属性的值不为null
     *
     * @param value
     * @param format 错误信息格式
     * @param args 错误信息参数
     * @param <T>
     * @return
     */
    public static <T> T notNullPropFmt(T value, String format, Object... args) {
        if (value == null) {
            throw new InvalidPropertyException(format, args);
        }

        return value;
    }

}
