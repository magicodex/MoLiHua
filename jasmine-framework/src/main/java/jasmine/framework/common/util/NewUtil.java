package jasmine.framework.common.util;

import jasmine.framework.common.constant.CollectionConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
     * 创建 Map 对象并返回
     *
     * @param args
     * @return
     * @param <K>
     * @param <V>
     */
    public static <K, V> Map<K, V> asMap(Object... args) {
        Map<K, V> map = map();
        K key;
        V value;

        for (int index = 0; index < args.length; index = index + 2) {
            key = (K) args[index];

            if (index + 1 < args.length) {
                value = (V) args[index + 1];
            } else {
                value = null;
            }

            map.put(key, value);
        }

        return map;
    }

    /**
     * 创建 Set 对象并返回
     *
     * @return
     * @param <T>
     */
    public static <T> Set<T> set() {
        return new HashSet<>(CollectionConstants.DEFAULT_SET_INITIAL_CAPACITY);
    }

    /**
     * 创建 Set 对象并返回
     *
     * @param initialCapacity
     * @return
     * @param <T>
     */
    public static <T> Set<T> set(int initialCapacity) {
        return new HashSet<>(initialCapacity);
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

    /**
     * 创建数组对象并返回
     *
     * @param array
     * @return
     * @param <T>
     */
    public static <T> T[] array(T... array) {
        return array;
    }

}
