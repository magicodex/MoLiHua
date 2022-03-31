package jasmine.framework.common.util.number;

/**
 * @author mh.z
 */
public class LongValue {
    private long value;

    public LongValue(long value) {
        this.value = value;
    }

    public long get() {
        return value;
    }

    public void set(long other) {
        this.value = other;
    }

    public void add(long other) {
        value = value + other;
    }

    public void subtract(long other) {
        value = value - other;
    }

}
