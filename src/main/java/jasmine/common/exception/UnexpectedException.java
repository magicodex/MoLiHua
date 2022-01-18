package jasmine.common.exception;

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

    public UnexpectedException(String message, Object... args) {
        super(message, args);
    }

    public UnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedException(Throwable cause) {
        super(cause);
    }

}
