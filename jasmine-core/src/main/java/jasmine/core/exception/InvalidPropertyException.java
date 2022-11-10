package jasmine.core.exception;

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

    public InvalidPropertyException(String messageOrKey, Object[] args) {
        super(DEFAULT_ERROR_CODE, messageOrKey, args);
    }

    public InvalidPropertyException(String errorCode, String messageOrKey, Object[] args) {
        super(errorCode, messageOrKey, args);
    }

    public InvalidPropertyException(String errorCode, String messageOrKey, Object[] args, Throwable cause) {
        super(errorCode, messageOrKey, args, cause);
    }

}
