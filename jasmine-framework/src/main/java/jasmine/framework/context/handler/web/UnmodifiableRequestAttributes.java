package jasmine.framework.context.handler.web;

import jasmine.core.exception.InvalidPropertyException;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Collection;
import java.util.Map;

/**
 * @author mh.z
 */
public class UnmodifiableRequestAttributes implements RequestAttributes {
    private String sessionId;
    private Map<String, Object> sessionAttributes;
    private Map<String, Object> requestAttributes;

    public UnmodifiableRequestAttributes(String sessionId, Map<String, Object> sessionAttributes,
                                         Map<String, Object> requestAttributes) {
        this.sessionId = sessionId;
        this.sessionAttributes = sessionAttributes;
        this.requestAttributes = requestAttributes;
    }

    @Override
    public Object getAttribute(String name, int scope) {
        Object attribute = null;

        if (scope == SCOPE_REQUEST) {
            attribute = requestAttributes.get(name);
        } else if (scope == SCOPE_SESSION) {
            attribute = sessionAttributes.get(name);
        } else {
            throw new InvalidPropertyException("scope(" + scope + ") invalid");
        }

        return attribute;
    }

    @Override
    public void setAttribute(String name, Object value, int scope) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAttribute(String name, int scope) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] getAttributeNames(int scope) {
        Collection<String> attributeNames = null;

        if (scope == SCOPE_REQUEST) {
            attributeNames = requestAttributes.keySet();
        } else if (scope == SCOPE_SESSION) {
            attributeNames = sessionAttributes.keySet();
        } else {
            throw new InvalidPropertyException("scope(" + scope + ") invalid");
        }

        return attributeNames.toArray(new String[0]);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback, int scope) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object resolveReference(String key) {
        // 因为无法避免通过该方法的返回结果修改数据，
        // 所以暂不支持该方法。
        throw new UnsupportedOperationException();
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public Object getSessionMutex() {
        // 因为无法避免通过该方法的返回结果修改数据，
        // 所以暂不支持该方法。
        throw new UnsupportedOperationException();
    }

}
