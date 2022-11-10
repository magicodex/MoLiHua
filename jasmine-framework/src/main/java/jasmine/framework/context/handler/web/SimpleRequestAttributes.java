package jasmine.framework.context.handler.web;

import jasmine.core.exception.InvalidParameterException;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mh.z
 */
public class SimpleRequestAttributes implements RequestAttributes {
    private String sessionId;
    private Map<String, Object> sessionAttributes;
    private Map<String, Object> requestAttributes;

    public SimpleRequestAttributes(String sessionId) {
        this.sessionId = sessionId;
        this.sessionAttributes = new HashMap<>();
        this.requestAttributes = new HashMap<>();
    }

    public SimpleRequestAttributes(String sessionId, Map<String, Object> sessionAttributes,
                                   Map<String, Object> requestAttributes) {
        this.sessionId = sessionId;
        this.sessionAttributes = sessionAttributes;
        this.requestAttributes = requestAttributes;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public Object getAttribute(String name, int scope) {
        Map<String, Object> attributeMap = getAttributeMap(scope);
        Object attribute = attributeMap.get(name);

        return attribute;
    }

    @Override
    public void setAttribute(String name, Object value, int scope) {
        Map<String, Object> attributeMap = getAttributeMap(scope);

        attributeMap.put(name, value);
    }

    @Override
    public void removeAttribute(String name, int scope) {
        Map<String, Object> attributeMap = getAttributeMap(scope);

        attributeMap.remove(name);
    }

    @Override
    public String[] getAttributeNames(int scope) {
        Map<String, Object> attributeMap = getAttributeMap(scope);
        Collection<String> attributeNames = attributeMap.keySet();

        return attributeNames.toArray(new String[0]);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback, int scope) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object resolveReference(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getSessionMutex() {
        throw new UnsupportedOperationException();
    }

    /**
     * 返回指定的键值集合
     *
     * @param scope
     * @return
     */
    protected Map<String, Object> getAttributeMap(int scope) {
        Map<String, Object> attributeMap;

        if (scope == SCOPE_REQUEST) {
            attributeMap = requestAttributes;
        } else if (scope == SCOPE_SESSION) {
            attributeMap = sessionAttributes;
        } else {
            throw new InvalidParameterException("scope(" + scope + ") invalid", null);
        }

        return attributeMap;
    }

}
