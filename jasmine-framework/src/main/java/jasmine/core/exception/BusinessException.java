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

    public BusinessException(String message, Object... args) {
        super(message, args);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

}
