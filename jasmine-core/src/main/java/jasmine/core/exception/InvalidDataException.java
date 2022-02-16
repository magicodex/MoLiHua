package jasmine.core.exception;

import jasmine.core.exception.type.ErrorType;
import jasmine.core.exception.type.SimpleErrorType;

/**
 * <p>
 * 无效数据，表示数据有错误。
 * </p>
 *
 * @author mh.z
 */
public class InvalidDataException extends UnexpectedException {
    /** 错误代码 */
    private static final String ERROR_CODE = "INVALID_DATA";
    /** 错误详情格式 */
    private static final String ERROR_DETAIL_FORMAT = "data %s[%s=%s] is invalid(reason %s)";
    /** 默认key名称 */
    private static final String DEFAULT_KEY_NAME = "key";
    /** 默认信息 */
    private static final String DEFAULT_MESSAGE_OR_KEY = "invalid data";

    public InvalidDataException() {
        //
    }

    public InvalidDataException(String messageOrKey, Object... args) {
        super(messageOrKey, args);
    }

    public InvalidDataException(String messageOrKey, Throwable cause) {
        super(messageOrKey, cause);
    }

    public InvalidDataException(Throwable cause) {
        super(cause);
    }

    public InvalidDataException(Class<?> clazz, Object key, String reason) {
        super(buildErrorType(clazz, DEFAULT_KEY_NAME, key, reason), DEFAULT_MESSAGE_OR_KEY);
    }

    public InvalidDataException(Class<?> clazz, String keyName, Object key, String reason) {
        super(buildErrorType(clazz, keyName, key, reason), DEFAULT_MESSAGE_OR_KEY);
    }

    /**
     * 创建错误类型
     *
     * @param clazz
     * @param keyName
     * @param key
     * @param reason
     * @return
     */
    protected static ErrorType buildErrorType(Class<?> clazz, String keyName,
                                              Object key, String reason) {
        String errorDetail = String.format(ERROR_DETAIL_FORMAT, clazz.getSimpleName(), keyName, key, reason);
        ErrorType errorType = new SimpleErrorType(ERROR_CODE, errorDetail);

        return errorType;
    }

}
