package jasmine.core.util.sort;

import jasmine.core.util.QObjectUtil;

import java.util.Comparator;
import java.util.function.Function;

/**
 * @author mh.z
 */
public class KeyComparator<T, K extends Comparable> implements Comparator<T> {
    /** 字段 */
    private Function<T, K> keyFunction;
    /** 升序 */
    private boolean asc;

    public KeyComparator(Function<T, K> keyFunction, boolean asc) {
        this.keyFunction = keyFunction;
        this.asc = asc;
    }

    @Override
    public int compare(T left, T right) {
        Comparable leftKey = keyFunction.apply(left);
        Comparable rightKey = keyFunction.apply(right);

        int result = QObjectUtil.compare(leftKey, rightKey);
        if (result != 0 && !asc) {
            result = (-1) * result;
        }

        return result;
    }

}
