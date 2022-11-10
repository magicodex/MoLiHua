package jasmine.core.exception;

/**
 * <p>
 * 不期望的异常，表示不应该出现的错误。
 * </p>
 *
 * @author mh.z
 */
public class UnexpectedException extends ApplicationException {
    /** 默认错误代码 */
    public static final String DEFAULT_ERROR_CODE = "UNEXPECTED_ERROR";

    public UnexpectedException(Throwable cause) {
        super(DEFAULT_ERROR_CODE, null, null, cause);
    }

    public UnexpectedException(String messageOrKey, Object[] args) {
        super(DEFAULT_ERROR_CODE, messageOrKey, args);
    }

    public UnexpectedException(String errorCode, String messageOrKey, Object[] args) {
        super(errorCode, messageOrKey, args);
    }

    public UnexpectedException(String errorCode, String messageOrKey, Object[] args, Throwable cause) {
        super(errorCode, messageOrKey, args, cause);
    }
}
