package jasmine.framework.remote.impl.mq;

import jasmine.framework.remote.mq.PublishMessageContext;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author mh.z
 */
public class DefaultPublishMessageContext implements PublishMessageContext {
    /** 类别 */
    private String category;
    /** 标识 */
    private String key;
    /** 属性 */
    private Map<String, Object> attributes;

    public DefaultPublishMessageContext(String category) {
        this(category, null);
    }

    public DefaultPublishMessageContext(String category, String key) {
        this.category = category;
        this.key = key;
        this.attributes = new HashMap<>();
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String computeKey(Supplier<String> supplier) {
        key = supplier.get();

        return key;
    }

    @Override
    public String computeKeyIfAbsent(Supplier<String> supplier) {
        if (key == null) {
            key = supplier.get();
        }

        return key;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

}
