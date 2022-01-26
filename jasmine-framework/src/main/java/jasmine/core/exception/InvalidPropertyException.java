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

    public InvalidPropertyException(String message, Object... args) {
        super(message, args);
    }

    public InvalidPropertyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPropertyException(Throwable cause) {
        super(cause);
    }

}
