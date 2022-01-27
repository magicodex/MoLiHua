package jasmine.core.exception;

import jasmine.core.util.QI18nUtil;

/**
 * @author mh.z
 */
public class ApplicationException extends RuntimeException {

    public ApplicationException() {
        //
    }

    public ApplicationException(String message, Object... args) {
        super(buildErrorMessage(message, args));
    }

    public ApplicationException(String message, Throwable cause) {
        super(buildErrorMessage(message, new Object[0]), cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    /**
     * 生成错误信息
     *
     * @param message
     * @param args
     * @return
     */
    protected static String buildErrorMessage(String message, Object[] args) {
        String returnErrorMessage = null;

        if (message == null) {
            return null;
        }

        // 符号"$"开头的是多语言信息key
        if (!message.startsWith("$")) {
            if (args.length > 0) {
                returnErrorMessage = String.format(message, args);
            } else {
                returnErrorMessage = message;
            }
        } else {
            returnErrorMessage = QI18nUtil.getMessage(message, args);
        }

        return returnErrorMessage;
    }

}
