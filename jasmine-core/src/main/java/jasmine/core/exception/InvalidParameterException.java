package jasmine.core.exception;

/**
 * <p>
 * 无效参数的异常，表示参数的值无效。
 * </p>
 *
 * @author mh.z
 */
public class InvalidParameterException extends UnexpectedException {
    /** 默认错误代码 */
    public static final String DEFAULT_ERROR_CODE = "INVALID_PARAMETER";

    public InvalidParameterException(Throwable cause) {
        super(DEFAULT_ERROR_CODE, null, null, cause);
    }

    public InvalidParameterException(String messageOrKey, Object[] args) {
        super(DEFAULT_ERROR_CODE, messageOrKey, args);
    }

    public InvalidParameterException(String errorCode, String messageOrKey, Object[] args) {
        super(errorCode, messageOrKey, args);
    }

    public InvalidParameterException(String errorCode, String messageOrKey, Object[] args, Throwable cause) {
        super(errorCode, messageOrKey, args, cause);
    }

}
