package jasmine.framework.validation;

import jasmine.core.exception.ApplicationException;
import org.springframework.validation.ObjectError;

import java.util.Collection;

/**
 * @author mh.z
 */
public class ValidationException extends ApplicationException {
    private Collection<ObjectError> errors;

    /** 默认错误代码 */
    private static final String DEFAULT_ERROR_CODE = "VALIDATE_FAILED";

    public ValidationException(Collection<ObjectError> errors) {
        super();
        this.errors = errors;
        this.errorCode = DEFAULT_ERROR_CODE;
        this.errorDetail = buildErrorDetail(errors);
    }

    public ValidationException(String messageOrKey, Collection<ObjectError> errors) {
        super(messageOrKey);
        this.errors = errors;
        this.errorCode = DEFAULT_ERROR_CODE;
        this.errorDetail = buildErrorDetail(errors);
    }

    public Collection<ObjectError> getErrors() {
        return errors;
    }

    /**
     * 生成错误详情
     *
     * @param errors
     * @return
     */
    public static String buildErrorDetail(Collection<ObjectError> errors) {
        return errors.toString();
    }

}
