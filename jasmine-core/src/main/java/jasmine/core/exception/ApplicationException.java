package jasmine.core.exception;

import jasmine.core.exception.type.ErrorType;
import jasmine.core.util.QI18nUtil;

/**
 * <p>
 * 应用异常，支持多语言。
 * </p>
 *
 * @author mh.z
 */
public class ApplicationException extends RuntimeException {
    /** 错误类型 */
    private ErrorType errorType;
    /** 多语言key前缀 */
    private static final String I18N_MESSAGE_KEY_PREFIX = "$";

    public ApplicationException() {
        //
    }

    public ApplicationException(String messageOrKey, Object... args) {
        super(buildErrorMessage(messageOrKey, args));
    }

    public ApplicationException(ErrorType errorType, String messageOrKey, Object... args) {
        super(buildErrorMessage(messageOrKey, args));
        this.errorType = errorType;
    }

    public ApplicationException(String messageOrKey, Throwable cause) {
        super(buildErrorMessage(messageOrKey, new Object[0]), cause);
    }

    public ApplicationException(ErrorType errorType, String messageOrKey, Throwable cause) {
        super(buildErrorMessage(messageOrKey, new Object[0]), cause);
        this.errorType = errorType;
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ApplicationException(ErrorType errorType, Throwable cause) {
        super(cause);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
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

        if (!messageOrKey.startsWith(I18N_MESSAGE_KEY_PREFIX)) {
            if (args.length > 0) {
                returnErrorMessage = String.format(messageOrKey, args);
            } else {
                returnErrorMessage = messageOrKey;
            }
        } else {
            // 获取多语言信息
            returnErrorMessage = QI18nUtil.getMessage(messageOrKey, args);
        }

        return returnErrorMessage;
    }

}
