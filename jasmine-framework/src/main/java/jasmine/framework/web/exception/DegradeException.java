package jasmine.framework.web.exception;

import jasmine.core.exception.ApplicationException;

/**
 * <p>
 * 降级异常。
 * </p>
 *
 * @author mh.z
 */
public class DegradeException extends ApplicationException {

    public DegradeException() {
        //
    }

    public DegradeException(String messageOrKey, Object... args) {
        super(messageOrKey, args);
    }

    public DegradeException(String errorCode, String messageOrKey, Object... args) {
        super(errorCode, messageOrKey, args);
    }

    public DegradeException(String messageOrKey, Throwable cause) {
        super(messageOrKey, cause);
    }

    public DegradeException(String errorCode, String messageOrKey, Throwable cause) {
        super(errorCode, messageOrKey, cause);
    }

    public DegradeException(Throwable cause) {
        super(cause);
    }

}
