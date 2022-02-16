package jasmine.framework.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 * <p>
 * 校验帮助类。
 * </p>
 *
 * @author mh.z
 */
public class ValidationHelper {
    private Errors errors;

    public ValidationHelper(Errors errors) {
        this.errors = errors;
    }

    public static ValidationHelper create(Errors errors) {
        return new ValidationHelper(errors);
    }

    public Errors getErrors() {
        return errors;
    }

    /**
     * 不能为空
     *
     * @param field
     */
    public void rejectIfEmpty(String field) {
        ValidationUtils.rejectIfEmpty(errors, field,
                "javax.validation.constraints.NotEmpty.message");
    }

    /**
     * 不能为空
     *
     * @param field
     */
    public void rejectIfBlank(String field) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, field,
                "javax.validation.constraints.NotBlank.message");
    }

}
