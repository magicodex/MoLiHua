package jasmine.framework.common.util.sort;

import java.util.function.Function;

/**
 * @author mh.z
 */
public class SortKey<T, K extends Comparable> {
    /** 字段 */
    private Function<T, K> keyFunction;
    /** 升序 */
    private boolean asc;

    public SortKey(Function<T, K> keyFunction, boolean asc) {
        this.keyFunction = keyFunction;
        this.asc = asc;
    }

    public Function<T, K> getKeyFunction() {
        return keyFunction;
    }

    public boolean getAsc() {
        return asc;
    }

    public static <T, K extends Comparable> SortKey<T, K> asc(Function<T, K> keyFunction) {
        return new SortKey<>(keyFunction, true);
    }

    public static <T, K extends Comparable> SortKey<T, K> desc(Function<T, K> keyFunction) {
        return new SortKey<>(keyFunction, false);
    }

}
