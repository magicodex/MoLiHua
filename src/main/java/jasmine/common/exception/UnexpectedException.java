package jasmine.common.exception;

/**
 * <p>
 * 不期望的异常，表示不应该出现的错误。
 * </p>
 */
public class UnexpectedException extends RuntimeException {

    public UnexpectedException() {
    }

    public UnexpectedException(String message) {
        super(message);
    }

    public UnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedException(Throwable cause) {
        super(cause);
    }

}
