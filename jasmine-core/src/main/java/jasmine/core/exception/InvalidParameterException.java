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

    public InvalidParameterException(String messageOrKey, Object... args) {
        super(messageOrKey, args);
    }

    public InvalidParameterException(String errorCode, String messageOrKey, Object... args) {
        super(errorCode, messageOrKey, args);
    }

    public InvalidParameterException(String messageOrKey, Throwable cause) {
        super(messageOrKey, cause);
    }

    public InvalidParameterException(String errorCode, String messageOrKey, Throwable cause) {
        super(errorCode, messageOrKey, cause);
    }

    public InvalidParameterException(Throwable cause) {
        super(cause);
    }

}
