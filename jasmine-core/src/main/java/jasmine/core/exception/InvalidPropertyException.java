package jasmine.core.exception;

import jasmine.core.exception.type.ErrorType;

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

    public InvalidPropertyException(ErrorType errorType, String messageOrKey, Object... args) {
        super(errorType, messageOrKey, args);
    }

    public InvalidPropertyException(String messageOrKey, Throwable cause) {
        super(messageOrKey, cause);
    }

    public InvalidPropertyException(ErrorType errorType, String messageOrKey, Throwable cause) {
        super(errorType, messageOrKey, cause);
    }

    public InvalidPropertyException(Throwable cause) {
        super(cause);
    }

    public InvalidPropertyException(ErrorType errorType, Throwable cause) {
        super(errorType, cause);
    }

}
