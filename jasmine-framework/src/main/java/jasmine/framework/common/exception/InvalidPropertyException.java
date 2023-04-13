package jasmine.framework.common.exception;

import javax.annotation.Nullable;

/**
 * <p>
 * 无效属性的异常，表示属性的值无效。
 * </p>
 *
 * @author mh.z
 */
public class InvalidPropertyException extends UnexpectedException {
    /** 默认错误代码 */
    public static final String DEFAULT_ERROR_CODE = "INVALID_PROPERTY";

    public InvalidPropertyException(Throwable cause) {
        super(DEFAULT_ERROR_CODE, null, null, cause);
    }

    public InvalidPropertyException(@Nullable String messageOrKey, @Nullable Object[] messageArgs) {
        super(DEFAULT_ERROR_CODE, messageOrKey, messageArgs);
    }

    public InvalidPropertyException(String errorCode, @Nullable String messageOrKey,
                                    @Nullable Object[] messageArgs) {
        super(errorCode, messageOrKey, messageArgs);
    }

    public InvalidPropertyException(String errorCode, @Nullable String messageOrKey,
                                    @Nullable Object[] messageArgs, Throwable cause) {
        super(errorCode, messageOrKey, messageArgs, cause);
    }

}
