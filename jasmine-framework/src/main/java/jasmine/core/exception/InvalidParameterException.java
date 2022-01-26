package jasmine.core.exception;

/**
 * <p>
 * 无效参数的异常，表示参数的值无效。
 * </p>
 *
 * @author mh.z
 */
public class InvalidParameterException extends UnexpectedException {

    public InvalidParameterException() {
        //
    }

    public InvalidParameterException(String message, Object... args) {
        super(message, args);
    }

    public InvalidParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParameterException(Throwable cause) {
        super(cause);
    }

}
