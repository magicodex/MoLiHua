package jasmine.framework.web.util.validation;

import jasmine.framework.common.util.NewUtil;
import org.springframework.validation.AbstractBindingResult;

import java.util.Map;

/**
 * <p>
 * 校验上下文。
 * </p>
 *
 * @author mh.z
 */
public class ValidationContext extends AbstractBindingResult {
    private Map<String, Object> parameters;

    public ValidationContext() {
        super("request parameters");
        this.parameters = NewUtil.map();
    }

    public void setParameter(String name, Object value) {
        parameters.put(name, value);
    }

    @Override
    public Object getTarget() {
        return parameters;
    }

    @Override
    protected Object getActualFieldValue(String field) {
        return parameters.get(field);
    }

}
