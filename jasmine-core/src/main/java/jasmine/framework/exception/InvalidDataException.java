package jasmine.framework.exception;

import javax.annotation.Nullable;

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
    /** 默认错误信息/错误信息key */
    public static final String DEFAULT_MESSAGE_OR_KEY = "data [{0}={1}] is invalid";

    public InvalidDataException(Throwable cause) {
        super(DEFAULT_ERROR_CODE, null, null, cause);
    }

    public InvalidDataException(@Nullable String messageOrKey, @Nullable Object[] messageArgs) {
        super(DEFAULT_ERROR_CODE, messageOrKey, messageArgs);
    }

    public InvalidDataException(String errorCode, @Nullable String messageOrKey,
                                @Nullable Object[] messageArgs) {
        super(errorCode, messageOrKey, messageArgs);
    }

    public InvalidDataException(String errorCode, @Nullable String messageOrKey,
                                @Nullable Object[] messageArgs, Throwable cause) {
        super(errorCode, messageOrKey, messageArgs, cause);
    }

    public InvalidDataException(Class<?> dataType, Object dataKey) {
        this(dataType, DEFAULT_KEY_NAME, dataKey);
    }

    public InvalidDataException(Class<?> dataType, String dataKeyName, Object dataKey) {
        super(DEFAULT_ERROR_CODE, DEFAULT_MESSAGE_OR_KEY, new Object[]{dataKeyName, dataKey});
        this.dataType = dataType;
        this.dataKeyName = dataKeyName;
        this.dataKey = dataKey;
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
     * 设置错误详情
     *
     * @param dataType
     * @param dataKey
     * @param detail
     * @return
     */
    public InvalidDataException withErrorDetail(Class<?> dataType, Object dataKey,
                                                @Nullable String detail) {
        return withErrorDetail(dataType, DEFAULT_KEY_NAME, dataKey, detail);
    }

    /**
     * 设置错误详情
     *
     * @param dataType
     * @param dataKeyName
     * @param dataKey
     * @param detail
     * @return
     */
    public InvalidDataException withErrorDetail(Class<?> dataType, String dataKeyName, Object dataKey,
                                                @Nullable String detail) {
        this.dataType = dataType;
        this.dataKeyName = dataKeyName;
        this.dataKey = dataKey;
        this.errorDetail = buildErrorDetail(dataType, dataKeyName, dataKey, detail);

        return this;
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
    public static String buildErrorDetail(Class<?> dataType, String dataKeyName, Object dataKey,
                                          @Nullable String detail) {
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
