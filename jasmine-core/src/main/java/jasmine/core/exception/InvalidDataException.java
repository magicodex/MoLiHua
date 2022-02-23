package jasmine.core.exception;

/**
 * <p>
 * 无效数据，表示数据有错误。
 * </p>
 *
 * @author mh.z
 */
public class InvalidDataException extends UnexpectedException {
    // 数据类型
    protected Class<?> dataType;
    // key名称
    protected String dataKeyName;
    // key值
    protected Object dataKey;

    /** 默认错误代码 */
    private static final String DEFAULT_ERROR_CODE = "INVALID_DATA";
    /** 默认key名称 */
    private static final String DEFAULT_KEY_NAME = "key";

    public InvalidDataException() {
        super(DEFAULT_ERROR_CODE);
    }

    public InvalidDataException(String messageOrKey, Object... args) {
        super(DEFAULT_ERROR_CODE, messageOrKey, args);
    }

    public InvalidDataException(String errorCode, String messageOrKey, Object... args) {
        super(errorCode, messageOrKey, args);
    }

    public InvalidDataException(String messageOrKey, Throwable cause) {
        super(DEFAULT_ERROR_CODE, messageOrKey, cause);
    }

    public InvalidDataException(String errorCode, String messageOrKey, Throwable cause) {
        super(errorCode, messageOrKey, cause);
    }

    public InvalidDataException(Throwable cause) {
        super(DEFAULT_ERROR_CODE, cause);
    }

    public InvalidDataException(Class<?> dataType, Object dataKey, String messageOrKey, Object... args) {
        this(dataType, DEFAULT_KEY_NAME, dataKey, messageOrKey, args);
    }

    public InvalidDataException(Class<?> dataType, String dataKeyName, Object dataKey, String messageOrKey, Object... args) {
        super(DEFAULT_ERROR_CODE, messageOrKey, args);
        this.dataType = dataType;
        this.dataKeyName = dataKeyName;
        this.dataKey = dataKey;
    }

    public void detail(Class<?> dataType, Object dataKey, String detail) {
        detail(dataType, DEFAULT_KEY_NAME, dataKey, detail);
    }

    public void detail(Class<?> dataType, String dataKeyName, Object dataKey, String detail) {
        this.dataType = dataType;
        this.dataKeyName = dataKeyName;
        this.dataKey = dataKey;
        this.errorDetail = detail;
    }

    public Class<?> getDataType() {
        return dataType;
    }

    public String getDataKeyName() {
        return dataKeyName;
    }

    public Object getDataKey() {
        return dataKey;
    }
}
