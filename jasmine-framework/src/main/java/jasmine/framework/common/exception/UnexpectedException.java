package jasmine.framework.common.exception;

import javax.annotation.Nullable;

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

    public UnexpectedException(@Nullable String messageOrKey, @Nullable Object[] messageArgs) {
        super(DEFAULT_ERROR_CODE, messageOrKey, messageArgs);
    }

    public UnexpectedException(String errorCode, @Nullable String messageOrKey,
                               @Nullable Object[] messageArgs) {
        super(errorCode, messageOrKey, messageArgs);
    }

    public UnexpectedException(String errorCode, @Nullable String messageOrKey,
                               @Nullable Object[] messageArgs, Throwable cause) {
        super(errorCode, messageOrKey, messageArgs, cause);
    }
}
