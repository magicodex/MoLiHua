package jasmine.core.util.wrapper;

/**
 * @author mh.z
 */
public class ObjectValue {
    private Object value;

    public ObjectValue(Object value) {
        this.value = value;
    }

    public Object get() {
        return value;
    }

    public void set(Object other) {
        this.value = other;
    }

}
