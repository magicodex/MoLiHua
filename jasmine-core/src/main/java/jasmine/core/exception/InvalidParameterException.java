package jasmine.core.exception;

import jasmine.core.exception.type.ErrorType;

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

    public InvalidParameterException(ErrorType errorType, String messageOrKey, Object... args) {
        super(errorType, messageOrKey, args);
    }

    public InvalidParameterException(String messageOrKey, Throwable cause) {
        super(messageOrKey, cause);
    }

    public InvalidParameterException(ErrorType errorType, String messageOrKey, Throwable cause) {
        super(errorType, messageOrKey, cause);
    }

    public InvalidParameterException(Throwable cause) {
        super(cause);
    }

    public InvalidParameterException(ErrorType errorType, Throwable cause) {
        super(errorType, cause);
    }

}
