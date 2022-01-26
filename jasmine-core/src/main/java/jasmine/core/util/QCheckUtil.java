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
     * @param message
     * @param <T>
     * @return
     */
    public static <T> T notNull(T value, String message) {
        if (value == null) {
            throw new UnexpectedException(message);
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
            throw new InvalidPropertyException(message);
        }

        return value;
    }

}
