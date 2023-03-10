package jasmine.core.util;

import jasmine.core.constant.CollectionConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 提供常见类的创建方法。
 * </p>
 *
 * @author mh.z
 */
public class NewUtil {

    //
    // TreeMap 的 key 不能为 null。
    // ConcurrentHashMap 的 key 和 value 都不能为 null。
    //

    /**
     * 创建 Map 对象并返回
     *
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> map() {
        return new HashMap<>(CollectionConstants.DEFAULT_MAP_INITIAL_CAPACITY);
    }

    /**
     * 创建 Map 对象并返回
     *
     * @param initialCapacity
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> map(int initialCapacity) {
        return new HashMap<>(initialCapacity);
    }

    /**
     * 创建 List 对象并返回
     *
     * @param <T>
     * @return
     */
    public static <T> List<T> list() {
        return new ArrayList<>(CollectionConstants.DEFAULT_LIST_INITIAL_CAPACITY);
    }

    /**
     * 创建 List 对象并返回
     *
     * @param initialCapacity
     * @param <T>
     * @return
     */
    public static <T> List<T> list(int initialCapacity) {
        return new ArrayList<>(initialCapacity);
    }

}
