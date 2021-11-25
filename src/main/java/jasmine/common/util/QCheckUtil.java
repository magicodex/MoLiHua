package jasmine.common.util;

import jasmine.common.exception.UnexpectedException;

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

}
