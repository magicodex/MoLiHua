package jasmine.core.exception;

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

    public BusinessException(String errorCode, String messageOrKey, Object... args) {
        super(errorCode, messageOrKey, args);
    }

    public BusinessException(String messageOrKey, Throwable cause) {
        super(messageOrKey, cause);
    }

    public BusinessException(String errorCode, String messageOrKey, Throwable cause) {
        super(errorCode, messageOrKey, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

}
