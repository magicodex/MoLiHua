package jasmine.framework.context.handler.web;

import jasmine.core.util.QCheckUtil;
import jasmine.framework.context.handler.ContextHandler;
import jasmine.framework.context.handler.ContextSnapshot;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author mh.z
 */
public class RequestContextHandler implements ContextHandler {

    @Override
    public ContextSnapshot copy() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        // 复制出新的对象
        if (requestAttributes != null) {
            String sessionId = requestAttributes.getSessionId();
            Map<String, Object> requestAttributeMap = copyAttributes(requestAttributes,
                    RequestAttributes.SCOPE_REQUEST);
            Map<String, Object> sessionAttributeMap = copyAttributes(requestAttributes,
                    RequestAttributes.SCOPE_SESSION);

            requestAttributes = new UnmodifiableRequestAttributes(sessionId,
                    requestAttributeMap, sessionAttributeMap);
        }

        RequestContextSnapshot snapshot = new RequestContextSnapshot(requestAttributes);
        return snapshot;
    }

    @Override
    public void init() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Override
    public void clear() {
        RequestContextHolder.resetRequestAttributes();
    }

    /**
     * 复制指定作用域的属性
     *
     * @param attributes
     * @param scope
     * @return
     */
    protected Map<String, Object> copyAttributes(RequestAttributes attributes, int scope) {
        QCheckUtil.notNull(attributes, "attributes null");
        String[] attributeNames = attributes.getAttributeNames(scope);

        if (attributeNames == null || attributeNames.length == 0) {
            return Collections.emptyMap();
        }

        Map<String, Object> attributeMap = new LinkedHashMap<>(attributeNames.length);
        String attributeName = null;
        Object attributeValue = null;

        for (int i = 0; i < attributeNames.length; i++) {
            attributeName = attributeNames[i];
            attributeValue = attributes.getAttribute(attributeName, scope);

            attributeMap.put(attributeName, attributeValue);
        }

        return attributeMap;
    }

}
