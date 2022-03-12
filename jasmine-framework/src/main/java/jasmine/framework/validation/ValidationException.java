package jasmine.framework.validation;

import jasmine.core.exception.ApplicationException;
import org.springframework.validation.Errors;

/**
 * @author mh.z
 */
public class ValidationException extends ApplicationException {
    private Errors errors;

    /** 默认错误代码 */
    private static final String DEFAULT_ERROR_CODE = "VALIDATE_FAILED";

    public ValidationException(Errors errors) {
        super();
        this.errors = errors;
        this.errorCode = DEFAULT_ERROR_CODE;
        this.errorDetail = buildErrorDetail(errors);
    }

    public ValidationException(String messageOrKey, Errors errors) {
        super(messageOrKey);
        this.errors = errors;
        this.errorCode = DEFAULT_ERROR_CODE;
        this.errorDetail = buildErrorDetail(errors);
    }

    public Errors getErrors() {
        return errors;
    }

    /**
     * 生成错误详情
     *
     * @param errors
     * @return
     */
    public static String buildErrorDetail(Errors errors) {
        return errors.toString();
    }

}
