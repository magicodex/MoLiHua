package jasmine.framework.web.exception;

import jasmine.framework.exception.ApplicationException;

/**
 * <p>
 * 降级异常。
 * </p>
 *
 * @author mh.z
 */
public class DegradeException extends ApplicationException {
    /** 默认错误代码 */
    public static final String DEFAULT_ERROR_CODE = "DEGRADE_ERROR";

    public DegradeException(Throwable cause) {
        super(DEFAULT_ERROR_CODE, null, null, cause);
    }

    public DegradeException(String messageOrKey, Object[] args) {
        super(DEFAULT_ERROR_CODE, messageOrKey, args);
    }

    public DegradeException(String errorCode, String messageOrKey, Object[] args) {
        super(errorCode, messageOrKey, args);
    }

    public DegradeException(String errorCode, String messageOrKey, Object[] args, Throwable cause) {
        super(errorCode, messageOrKey, args, cause);
    }

}
