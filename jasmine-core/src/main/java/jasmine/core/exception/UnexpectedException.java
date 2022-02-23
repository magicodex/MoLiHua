package jasmine.core.exception;

/**
 * <p>
 * 不期望的异常，表示不应该出现的错误。
 * </p>
 *
 * @author mh.z
 */
public class UnexpectedException extends ApplicationException {

    public UnexpectedException() {
        //
    }

    public UnexpectedException(String messageOrKey, Object... args) {
        super(messageOrKey, args);
    }

    public UnexpectedException(String errorCode, String messageOrKey, Object... args) {
        super(errorCode, messageOrKey, args);
    }

    public UnexpectedException(String messageOrKey, Throwable cause) {
        super(messageOrKey, cause);
    }

    public UnexpectedException(String errorCode, String messageOrKey, Throwable cause) {
        super(errorCode, messageOrKey, cause);
    }

    public UnexpectedException(Throwable cause) {
        super(cause);
    }

}
