package jasmine.core.exception;

import jasmine.core.exception.type.ErrorType;

/**
 * <p>
 * 不期望的异常，表示不应该出现的错误。
 * </p>
 *
 * @author mh.z
 */
public class UnexpectedException extends ApplicationException {

    public UnexpectedException() {
        //
    }

    public UnexpectedException(String messageOrKey, Object... args) {
        super(messageOrKey, args);
    }

    public UnexpectedException(ErrorType errorType, String messageOrKey, Object... args) {
        super(errorType, messageOrKey, args);
    }

    public UnexpectedException(String messageOrKey, Throwable cause) {
        super(messageOrKey, cause);
    }

    public UnexpectedException(ErrorType errorType, String messageOrKey, Throwable cause) {
        super(errorType, messageOrKey, cause);
    }

    public UnexpectedException(Throwable cause) {
        super(cause);
    }

    public UnexpectedException(ErrorType errorType, Throwable cause) {
        super(errorType, cause);
    }

}
