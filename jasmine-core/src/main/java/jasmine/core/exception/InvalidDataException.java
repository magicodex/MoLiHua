package jasmine.core.exception;

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
    /** 默认key名称 */
    private static final String DEFAULT_KEY_NAME = "key";

    protected Class<?> clazz;
    protected String keyName;
    protected Object key;

    public InvalidDataException() {
        super(ERROR_CODE);
    }

    public InvalidDataException(String messageOrKey, Object... args) {
        super(ERROR_CODE, messageOrKey, args);
    }

    public InvalidDataException(String errorCode, String messageOrKey, Object... args) {
        super(errorCode, messageOrKey, args);
    }

    public InvalidDataException(String messageOrKey, Throwable cause) {
        super(ERROR_CODE, messageOrKey, cause);
    }

    public InvalidDataException(String errorCode, String messageOrKey, Throwable cause) {
        super(errorCode, messageOrKey, cause);
    }

    public InvalidDataException(Throwable cause) {
        super(ERROR_CODE, cause);
    }

    public InvalidDataException(Class<?> clazz, Object key, String messageOrKey, Object... args) {
        this(clazz, DEFAULT_KEY_NAME, key, messageOrKey, args);
    }

    public InvalidDataException(Class<?> clazz, String keyName, Object key, String messageOrKey, Object... args) {
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
