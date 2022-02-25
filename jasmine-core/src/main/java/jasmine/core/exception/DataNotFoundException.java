package jasmine.core.exception;

/**
 * <p>
 * 未找到数据的异常，表示未找到数据。
 * </p>
 *
 * @author mh.z
 */
public class DataNotFoundException extends UnexpectedException {
    // 数据类型
    protected Class<?> dataType;
    // key名称
    protected String dataKeyName;
    // key值
    protected Object dataKey;

    /** 默认错误代码 */
    private static final String DEFAULT_ERROR_CODE = "DATA_NOT_FOUND";
    /** 默认key名称 */
    private static final String DEFAULT_KEY_NAME = "key";

    public DataNotFoundException() {
        //
    }

    public DataNotFoundException(String messageOrKey, Object... args) {
        super(DEFAULT_ERROR_CODE, messageOrKey, args);
    }

    public DataNotFoundException(String errorCode, String messageOrKey, Object... args) {
        super(errorCode, messageOrKey, args);
    }

    public DataNotFoundException(String messageOrKey, Throwable cause) {
        super(DEFAULT_ERROR_CODE, messageOrKey, cause);
    }

    public DataNotFoundException(String errorCode, String messageOrKey, Throwable cause) {
        super(errorCode, messageOrKey, cause);
    }

    public DataNotFoundException(Throwable cause) {
        super(DEFAULT_ERROR_CODE, cause);
    }

    public DataNotFoundException(Class<?> dataType, Object dataKey, String messageOrKey, Object... args) {
        this(dataType, DEFAULT_KEY_NAME, dataKey, messageOrKey, args);
    }

    public DataNotFoundException(Class<?> dataType, String dataKeyName, Object dataKey,
                                 String messageOrKey, Object... args) {
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
        StringBuilder builder = new StringBuilder("not found data ");

        builder.append(dataType.getSimpleName());
        builder.append('[');
        builder.append(dataKeyName);
        builder.append('=');
        builder.append(dataKey);
        builder.append(']');

        if (detail != null) {
            builder.append(", ");
            builder.append(detail);
        }

        return builder.toString();
    }

}
