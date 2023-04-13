package jasmine.framework.exception;

import jasmine.framework.i18n.I18nConstants;
import jasmine.framework.common.util.I18nUtil;

import javax.annotation.Nullable;
import java.text.MessageFormat;

/**
 * <p>
 * 应用异常，支持多语言。
 * </p>
 *
 * @author mh.z
 */
public class ApplicationException extends RuntimeException {
    /** 错误代码 */
    protected String errorCode;
    /** 错误详情  */
    protected String errorDetail;
    /** 错误信息/错误信息key */
    protected String messageOrKey;
    /** 错误信息参数 */
    protected Object[] messageArgs;

    /** 默认错误代码 */
    public static final String DEFAULT_ERROR_CODE = "APPLICATION_ERROR";

    public ApplicationException(Throwable cause) {
        this(DEFAULT_ERROR_CODE, null, null, cause);
    }

    public ApplicationException(@Nullable String messageOrKey, @Nullable Object[] messageArgs) {
        this(DEFAULT_ERROR_CODE, messageOrKey, messageArgs);
    }

    public ApplicationException(String errorCode, @Nullable String messageOrKey,
                                @Nullable Object[] messageArgs) {
        super(buildErrorMessage(messageOrKey, messageArgs));
        this.errorCode = errorCode;
        this.errorDetail = null;
        this.messageOrKey = messageOrKey;
        this.messageArgs = messageArgs;
    }

    public ApplicationException(String errorCode, @Nullable String messageOrKey,
                                @Nullable Object[] messageArgs, Throwable cause) {
        super(buildErrorMessage(messageOrKey, messageArgs), cause);
        this.errorCode = errorCode;
        this.errorDetail = null;
        this.messageOrKey = messageOrKey;
        this.messageArgs = messageArgs;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public String getMessageOrKey() {
        return messageOrKey;
    }

    public Object[] getMessageArgs() {
        return messageArgs;
    }

    public ApplicationException withErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;

        return this;
    }

    /**
     * 生成错误信息
     *
     * @param messageOrKey
     * @param messageArgs
     * @return
     */
    public static String buildErrorMessage(@Nullable String messageOrKey, @Nullable Object[] messageArgs) {
        String errorMessage = null;

        if (messageOrKey == null) {
            return null;
        }

        if (!messageOrKey.startsWith(I18nConstants.I18N_MESSAGE_KEY_PREFIX)) {
            if (messageArgs != null && messageArgs.length > 0) {
                errorMessage = MessageFormat.format(messageOrKey, messageArgs);
            } else {
                errorMessage = messageOrKey;
            }
        } else {
            // 获取多语言信息
            errorMessage = I18nUtil.getMessage(messageOrKey, messageArgs);
        }

        return errorMessage;
    }

}
