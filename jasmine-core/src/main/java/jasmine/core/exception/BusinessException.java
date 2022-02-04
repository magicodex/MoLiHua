package jasmine.core.exception;

import jasmine.core.exception.type.ErrorType;

/**
 * <p>
 * 业务异常，在业务校验不通过时可抛出该异常。
 * </p>
 *
 * @author mh.z
 */
public class BusinessException extends ApplicationException {

    public BusinessException() {
        //
    }

    public BusinessException(String messageOrKey, Object... args) {
        super(messageOrKey, args);
    }

    public BusinessException(ErrorType errorType, String messageOrKey, Object... args) {
        super(errorType, messageOrKey, args);
    }

    public BusinessException(String messageOrKey, Throwable cause) {
        super(messageOrKey, cause);
    }

    public BusinessException(ErrorType errorType, String messageOrKey, Throwable cause) {
        super(errorType, messageOrKey, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(ErrorType errorType, Throwable cause) {
        super(errorType, cause);
    }

}
