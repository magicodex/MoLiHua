package jasmine.common.exception;

import jasmine.common.util.Q;

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
        super(message, cause);
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
        String returnMessage = null;

        if (!message.startsWith("$")) {
            if (args.length > 0) {
                returnMessage = String.format(message, args);
            } else {
                returnMessage = message;
            }
        } else {
            returnMessage = Q.tr(message, args);
        }

        return returnMessage;
    }

}
