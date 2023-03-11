package jasmine.core.exception;

import javax.annotation.Nullable;

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

    public BusinessException(@Nullable String messageOrKey, @Nullable Object[] messageArgs) {
        super(DEFAULT_ERROR_CODE, messageOrKey, messageArgs);
    }

    public BusinessException(String errorCode, @Nullable String messageOrKey,
                             @Nullable Object[] messageArgs) {
        super(errorCode, messageOrKey, messageArgs);
    }

    public BusinessException(String errorCode, @Nullable String messageOrKey,
                             @Nullable Object[] messageArgs, Throwable cause) {
        super(errorCode, messageOrKey, messageArgs, cause);
    }

}
