package jasmine.core.exception;

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
    /** 默认key名称 */
    private static final String DEFAULT_KEY_NAME = "key";

    protected Class<?> clazz;
    protected String keyName;
    protected Object key;

    public DataNotFoundException() {
    }

    public DataNotFoundException(String messageOrKey, Object... args) {
        super(ERROR_CODE, messageOrKey, args);
    }

    public DataNotFoundException(String errorCode, String messageOrKey, Object... args) {
        super(errorCode, messageOrKey, args);
    }

    public DataNotFoundException(String messageOrKey, Throwable cause) {
        super(ERROR_CODE, messageOrKey, cause);
    }

    public DataNotFoundException(String errorCode, String messageOrKey, Throwable cause) {
        super(errorCode, messageOrKey, cause);
    }

    public DataNotFoundException(Throwable cause) {
        super(ERROR_CODE, cause);
    }

    public DataNotFoundException(Class<?> clazz, Object key, String messageOrKey, Object... args) {
        this(clazz, DEFAULT_KEY_NAME, key, messageOrKey, args);
    }

    public DataNotFoundException(Class<?> clazz, String keyName, Object key, String messageOrKey, Object... args) {
        this(ERROR_CODE, messageOrKey, args);
        this.clazz = clazz;
        this.keyName = keyName;
        this.key = key;
    }

    public void detail(Class<?> clazz, Object key, String detail) {
        detail(clazz, DEFAULT_KEY_NAME, key, detail);
    }

    public void detail(Class<?> clazz, String keyName, Object key, String detail) {
        this.clazz = clazz;
        this.keyName = keyName;
        this.key = key;
        this.errorDetail = detail;
    }

}
