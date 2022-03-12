package jasmine.framework.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 * @author mh.z
 */
public class ValidationField {
    private ValidationHelper validationHelper;
    private Errors errors;
    private String fieldName;

    public ValidationField(ValidationHelper validationHelper,
                           Errors errors, String fieldName) {
        this.validationHelper = validationHelper;
        this.errors = errors;
        this.fieldName = fieldName;
    }

    public ValidationField field(String name) {
        return validationHelper.field(name);
    }

    public ValidationField field(String name, Object value) {
        return validationHelper.field(name, value);
    }

    /**
     * 不能为空字符串
     *
     * @param field
     * @return
     */
    public ValidationField rejectIfEmpty(String field) {
        ValidationUtils.rejectIfEmpty(errors, field,
                "javax.validation.constraints.NotEmpty.message");

        return this;
    }

    /**
     * 不能为空字符串或空白符
     *
     * @param field
     * @return
     */
    public ValidationField rejectIfBlank(String field) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, field,
                "javax.validation.constraints.NotBlank.message");

        return this;
    }

}
