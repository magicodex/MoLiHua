package jasmine.core.exception;

/**
 * <p>
 * 无效属性的异常，表示属性的值无效。
 * </p>
 *
 * @author mh.z
 */
public class InvalidPropertyException extends UnexpectedException {

    public InvalidPropertyException() {
        //
    }

    public InvalidPropertyException(String messageOrKey, Object... args) {
        super(messageOrKey, args);
    }

    public InvalidPropertyException(String errorCode, String messageOrKey, Object... args) {
        super(errorCode, messageOrKey, args);
    }

    public InvalidPropertyException(String messageOrKey, Throwable cause) {
        super(messageOrKey, cause);
    }

    public InvalidPropertyException(String errorCode, String messageOrKey, Throwable cause) {
        super(errorCode, messageOrKey, cause);
    }

    public InvalidPropertyException(Throwable cause) {
        super(cause);
    }
    
}
