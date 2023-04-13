package jasmine.framework.exception;

import javax.annotation.Nullable;

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

    public InvalidParameterException(@Nullable String messageOrKey, @Nullable Object[] messageArgs) {
        super(DEFAULT_ERROR_CODE, messageOrKey, messageArgs);
    }

    public InvalidParameterException(String errorCode, @Nullable String messageOrKey,
                                     @Nullable Object[] messageArgs) {
        super(errorCode, messageOrKey, messageArgs);
    }

    public InvalidParameterException(String errorCode, @Nullable String messageOrKey,
                                     @Nullable Object[] messageArgs, Throwable cause) {
        super(errorCode, messageOrKey, messageArgs, cause);
    }

}
