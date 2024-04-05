package jasmine.framework.common.exception;

import cn.hutool.core.util.ClassUtil;
import jasmine.framework.common.constant.CommonMessageConstants;
import jasmine.framework.common.util.StringUtil;

import javax.annotation.Nullable;

/**
 * <p>
 * 未找到数据的异常，表示未找到数据。
 * </p>
 *
 * @author mh.z
 */
public class DataNotFoundException extends UnexpectedException {
    /** 数据类型 */
    protected Class<?> dataType;
    /** key名称 */
    protected String dataKeyName;
    /** key值 */
    protected Object dataKey;

    /** 默认错误代码 */
    public static final String DEFAULT_ERROR_CODE = "DATA_NOT_FOUND";
    /** 默认key名称 */
    public static final String DEFAULT_KEY_NAME = "key";
    /** 默认错误信息/错误信息key */
    public static final String DEFAULT_MESSAGE_OR_KEY = CommonMessageConstants.NOT_FOUND_THE_DATA;

    public DataNotFoundException(Throwable cause) {
        super(DEFAULT_ERROR_CODE, null, null, cause);
    }

    public DataNotFoundException(@Nullable String messageOrKey, @Nullable Object[] messageArgs) {
        super(DEFAULT_ERROR_CODE, messageOrKey, messageArgs);
    }

    public DataNotFoundException(String errorCode, @Nullable String messageOrKey,
                                 @Nullable Object[] messageArgs) {
        super(errorCode, messageOrKey, messageArgs);
    }

    public DataNotFoundException(String errorCode, @Nullable String messageOrKey,
                                 @Nullable Object[] messageArgs, Throwable cause) {
        super(errorCode, messageOrKey, messageArgs, cause);
    }

    public DataNotFoundException(Class<?> dataType, Object dataKey) {
        this(dataType, DEFAULT_KEY_NAME, dataKey);
    }

    public DataNotFoundException(Class<?> dataType, String dataKeyName, Object dataKey) {
        super(DEFAULT_ERROR_CODE, DEFAULT_MESSAGE_OR_KEY, new Object[]{ClassUtil.getClassName(dataType, true),
                dataKeyName, StringUtil.toString(dataKey)});
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
    public DataNotFoundException withErrorDetail(Class<?> dataType, Object dataKey,
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
    public DataNotFoundException withErrorDetail(Class<?> dataType, String dataKeyName, Object dataKey,
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
