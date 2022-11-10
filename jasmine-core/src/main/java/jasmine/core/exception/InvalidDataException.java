package jasmine.core.exception;

/**
 * <p>
 * 无效数据，表示数据有错误。
 * </p>
 *
 * @author mh.z
 */
public class InvalidDataException extends UnexpectedException {
    /** 数据类型 */
    protected Class<?> dataType;
    /** key名称 */
    protected String dataKeyName;
    /** key值 */
    protected Object dataKey;

    /** 默认错误代码 */
    public static final String DEFAULT_ERROR_CODE = "INVALID_DATA";
    /** 默认key名称 */
    public static final String DEFAULT_KEY_NAME = "key";

    public InvalidDataException(Throwable cause) {
        super(DEFAULT_ERROR_CODE, null, null, cause);
    }

    public InvalidDataException(String messageOrKey, Object[] args) {
        super(DEFAULT_ERROR_CODE, messageOrKey, args);
    }

    public InvalidDataException(String errorCode, String messageOrKey, Object[] args) {
        super(errorCode, messageOrKey, args);
    }

    public InvalidDataException(String errorCode, String messageOrKey, Object[] args, Throwable cause) {
        super(errorCode, messageOrKey, args, cause);
    }

    public InvalidDataException(Class<?> dataType, Object dataKey) {
        this(dataType, DEFAULT_KEY_NAME, dataKey, null, null);
    }

    public InvalidDataException(Class<?> dataType, String dataKeyName, Object dataKey) {
        this(dataType, dataKeyName, dataKey, null, null);
    }

    public InvalidDataException(Class<?> dataType, Object dataKey, String messageOrKey, Object[] args) {
        this(dataType, DEFAULT_KEY_NAME, dataKey, messageOrKey, args);
    }

    public InvalidDataException(Class<?> dataType, String dataKeyName, Object dataKey,
                                String messageOrKey, Object[] args) {
        super(DEFAULT_ERROR_CODE, messageOrKey, args);
        this.dataType = dataType;
        this.dataKeyName = dataKeyName;
        this.dataKey = dataKey;
        this.errorDetail = buildErrorDetail(dataType, dataKeyName, dataKey, null);
    }

    public void detail(Class<?> dataType, Object dataKey, String detail) {
        detail(dataType, DEFAULT_KEY_NAME, dataKey, detail);
    }

    public void detail(Class<?> dataType, String dataKeyName, Object dataKey, String detail) {
        this.dataType = dataType;
        this.dataKeyName = dataKeyName;
        this.dataKey = dataKey;
        this.errorDetail = buildErrorDetail(dataType, dataKeyName, dataKey, detail);
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

    /**
     * 生成错误详情
     *
     * @param dataType
     * @param dataKeyName
     * @param dataKey
     * @param detail
     * @return
     */
    protected String buildErrorDetail(Class<?> dataType, String dataKeyName, Object dataKey, String detail) {
        StringBuilder builder = new StringBuilder("data ");

        builder.append(dataType.getSimpleName());
        builder.append('[');
        builder.append(dataKeyName);
        builder.append('=');
        builder.append(dataKey);
        builder.append(']');
        builder.append(" is invalid");

        if (detail != null) {
            builder.append(", ");
            builder.append(detail);
        }

        return builder.toString();
    }

}
