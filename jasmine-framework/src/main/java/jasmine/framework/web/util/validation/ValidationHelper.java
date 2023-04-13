package jasmine.framework.web.util.validation;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.web.entity.WebResult;
import jasmine.framework.web.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author mh.z
 */
public class ValidationHelper {
    private ValidationContext context;
    private Errors errors;

    public ValidationHelper(ValidationContext context, Errors errors) {
        this.context = context;
        this.errors = errors;
    }

    public static ValidationHelper create() {
        ValidationContext context = new ValidationContext();
        ValidationHelper helper = new ValidationHelper(context, null);

        return helper;
    }

    public static ValidationHelper create(Errors errors) {
        CheckUtil.notNull(errors, "errors null");
        ValidationContext context = new ValidationContext();
        ValidationHelper helper = new ValidationHelper(context, errors);

        return helper;
    }

    /**
     * 返回要校验的字段
     *
     * @param name
     * @return
     */
    public ValidationField field(String name) {
        ValidationField field = null;

        if (errors != null) {
            field = new ValidationField(this, errors, name);
        } else {
            field = new ValidationField(this, context, name);
        }

        return field;
    }

    /**
     * 返回要校验的字段
     *
     * @param name
     * @param value
     * @return
     */
    public ValidationField field(String name, Object value) {
        context.setParameter(name, value);
        ValidationField field = new ValidationField(this, context, name);

        return field;
    }

    /**
     * 判断是否有错误
     *
     * @return
     */
    public boolean hasErrors() {
        if (context.hasErrors()) {
            return true;
        }

        if (errors != null) {
            return errors.hasErrors();
        }

        return false;
    }

    /**
     * 返回所有的错误
     *
     * @return
     */
    public Collection<ObjectError> getAllErrors() {
        List<ObjectError> errorList = new ArrayList<>();
        errorList.addAll(context.getAllErrors());

        if (errors != null) {
            errorList.addAll(errors.getAllErrors());
        }

        return errorList;
    }

    /**
     * 转换成 ResponseEntity 对象
     *
     * @param <T>
     * @return
     */
    public <T> ResponseEntity<WebResult<T>> toEntity() {
        WebResult<T> result = WebResult.error(ValidationException.DEFAULT_ERROR_CODE, "validate failed");
        Collection<ObjectError> allErrors = getAllErrors();
        result.setErrorDetail(ValidationException.buildErrorDetail(allErrors));

        return result.toEntity();
    }

    /**
     * 转换成 ResponseEntity 对象
     *
     * @param <T>
     * @return
     */
    public <T> ResponseEntity<WebResult<T>> toOkEntity() {
        WebResult<T> result = WebResult.error(ValidationException.DEFAULT_ERROR_CODE, "validate failed");
        Collection<ObjectError> allErrors = getAllErrors();
        result.setErrorDetail(ValidationException.buildErrorDetail(allErrors));

        return result.toOkEntity();
    }

}
