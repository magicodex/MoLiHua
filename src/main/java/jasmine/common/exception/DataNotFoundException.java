package jasmine.common.exception;

/**
 * @author mh.z
 */
public class DataNotFoundException extends UnexpectedException {

    public DataNotFoundException() {
        //
    }

    public DataNotFoundException(String message, Object... args) {
        super(message, args);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotFoundException(Throwable cause) {
        super(cause);
    }

    public DataNotFoundException(Class<?> clazz, Object key) {
        this(clazz, "key", key);
    }

    public DataNotFoundException(Class<?> clazz, String keyName, Object key) {
        this("not found data %s[%s=%s]", clazz.getSimpleName(), keyName, key);
    }

}
