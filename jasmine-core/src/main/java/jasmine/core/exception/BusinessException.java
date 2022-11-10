package jasmine.core.exception;

/**
 * <p>
 * 业务异常，在业务校验不通过时可抛出该异常。
 * </p>
 *
 * @author mh.z
 */
public class BusinessException extends ApplicationException {
    /** 默认错误代码 */
    public static final String DEFAULT_ERROR_CODE = "BUSINESS_ERROR";

    public BusinessException(Throwable cause) {
        super(DEFAULT_ERROR_CODE, null, null, cause);
    }

    public BusinessException(String messageOrKey, Object[] args) {
        super(DEFAULT_ERROR_CODE, messageOrKey, args);
    }

    public BusinessException(String errorCode, String messageOrKey, Object[] args) {
        super(errorCode, messageOrKey, args);
    }

    public BusinessException(String errorCode, String messageOrKey, Object[] args, Throwable cause) {
        super(errorCode, messageOrKey, args, cause);
    }

}
