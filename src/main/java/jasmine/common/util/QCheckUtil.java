package jasmine.common.util;

import jasmine.common.exception.UnexpectedException;

public class QCheckUtil {

    public static <T> T notNull(T value, String message) {
        if (value == null) {
            throw new UnexpectedException(message);
        }

        return value;
    }

}
