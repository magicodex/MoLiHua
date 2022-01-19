package jasmine.common.exception;

/**
 * <p>
 * 无效数据，表示数据有错误。
 * </p>
 *
 * @author mh.z
 */
public class InvalidDataException extends UnexpectedException {

    public InvalidDataException() {
        //
    }

    public InvalidDataException(String message, Object... args) {
        super(message, args);
    }

    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataException(Throwable cause) {
        super(cause);
    }

    public InvalidDataException(Class<?> clazz, Object key, String reason) {
        this(clazz, "key", key, reason);
    }

    public InvalidDataException(Class<?> clazz, String keyName, Object key, String reason) {
        this("data %s[%s=%s] is invalid(reason %s)", clazz.getSimpleName(), keyName, key, reason);
    }

}
