package jasmine.core.exception;

import jasmine.core.exception.type.ErrorType;
import jasmine.core.exception.type.SimpleErrorType;

/**
 * <p>
 * 未找到数据的异常，表示未找到数据。
 * </p>
 *
 * @author mh.z
 */
public class DataNotFoundException extends UnexpectedException {
    /** 错误代码 */
    private static final String ERROR_CODE = "DATA_NOT_FOUND";
    /** 错误详情格式 */
    private static final String ERROR_DETAIL_FORMAT = "not found data %s[%s=%s]";
    /** 默认key名称 */
    private static final String DEFAULT_KEY_NAME = "key";
    /** 默认信息 */
    private static final String DEFAULT_MESSAGE = "data not found";

    public DataNotFoundException() {
        //
    }

    public DataNotFoundException(String messageOrKey, Object... args) {
        super(messageOrKey, args);
    }

    public DataNotFoundException(String messageOrKey, Throwable cause) {
        super(messageOrKey, cause);
    }

    public DataNotFoundException(Throwable cause) {
        super(cause);
    }

    public DataNotFoundException(Class<?> clazz, Object key) {
        super(buildErrorType(clazz, DEFAULT_KEY_NAME, key), DEFAULT_MESSAGE);
    }

    public DataNotFoundException(Class<?> clazz, String keyName, Object key) {
        super(buildErrorType(clazz, keyName, key), DEFAULT_MESSAGE);
    }

    /**
     * 创建错误类型
     *
     * @param clazz
     * @param keyName
     * @param key
     * @return
     */
    protected static ErrorType buildErrorType(Class<?> clazz, String keyName, Object key) {
        String errorDetail = String.format(ERROR_DETAIL_FORMAT, clazz.getSimpleName(), keyName, key);
        ErrorType errorType = new SimpleErrorType(ERROR_CODE, errorDetail);

        return errorType;
    }

}
