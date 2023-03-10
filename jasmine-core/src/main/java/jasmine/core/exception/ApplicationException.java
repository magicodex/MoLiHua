package jasmine.core.exception;

import jasmine.core.i18n.I18nConstants;
import jasmine.core.util.I18nUtil;
import jasmine.core.util.StringUtil;

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

    /** 默认错误代码 */
    public static final String DEFAULT_ERROR_CODE = "APPLICATION_ERROR";

    public ApplicationException(Throwable cause) {
        super(cause);
        this.errorCode = DEFAULT_ERROR_CODE;
    }

    public ApplicationException(String messageOrKey, Object[] args) {
        super(buildErrorMessage(messageOrKey, args));
        this.errorCode = DEFAULT_ERROR_CODE;
    }

    public ApplicationException(String errorCode, String messageOrKey, Object[] args) {
        super(buildErrorMessage(messageOrKey, args));
        this.errorCode = errorCode;
    }

    public ApplicationException(String errorCode, String messageOrKey, Object[] args, Throwable cause) {
        super(buildErrorMessage(messageOrKey, args), cause);
        this.errorCode = errorCode;
    }

    public ApplicationException detail(String detail) {
        this.errorDetail = detail;

        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    /**
     * 生成错误信息
     *
     * @param messageOrKey
     * @param args
     * @return
     */
    protected static String buildErrorMessage(String messageOrKey, Object[] args) {
        String returnErrorMessage = null;

        if (messageOrKey == null) {
            return null;
        }

        if (!messageOrKey.startsWith(I18nConstants.I18N_MESSAGE_KEY_PREFIX)) {
            if (args != null && args.length > 0) {
                returnErrorMessage = String.format(messageOrKey, args);
            } else {
                returnErrorMessage = messageOrKey;
            }
        } else {
            // 获取多语言信息
            returnErrorMessage = I18nUtil.getMessage(messageOrKey, args);
        }

        return returnErrorMessage;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());

        if (StringUtil.isNotEmpty(errorDetail)) {
            builder.append(" (");
            builder.append(errorDetail);
            builder.append(")");
        }

        return builder.toString();
    }

}
