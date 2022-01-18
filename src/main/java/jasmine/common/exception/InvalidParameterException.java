package jasmine.common.exception;

/**
 * @author mh.z
 */
public class InvalidParameterException extends UnexpectedException {

    public InvalidParameterException() {
        //
    }

    public InvalidParameterException(String message, Object... args) {
        super(message, args);
    }

    public InvalidParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParameterException(Throwable cause) {
        super(cause);
    }

}
