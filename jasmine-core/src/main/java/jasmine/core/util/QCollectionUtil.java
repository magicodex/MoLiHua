package jasmine.core.util;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <p>
 * 集合工具类。
 * </p>
 *
 * @author mh.z
 */
public class QCollectionUtil extends CollUtil {

    /**
     * 映射集合里的元素成其它类型的元素
     *
     * @param collection
     * @param function
     * @param <E>
     * @param <R>
     * @return
     */
    public static <E, R> List<R> mapToList(Collection<E> collection, Function<E, R> function) {
        QCheckUtil.notNull(function, "function null");
        List<R> returnList = null;

        if (isNotEmpty(collection)) {
            returnList = new ArrayList<>();

            for (E item : collection) {
                if (item != null) {
                    returnList.add(function.apply(item));
                } else {
                    returnList.add(null);
                }
            }
        } else {
            returnList = Collections.emptyList();
        }

        return returnList;
    }

    /**
     * 返回满足断言的元素
     *
     * @param collection
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> List<T> chooseToList(Collection<T> collection, Predicate<T> predicate) {
        QCheckUtil.notNull(predicate, "predicate null");
        List<T> returnList = null;

        if (isNotEmpty(collection)) {
            returnList = collection.stream().filter(predicate).collect(Collectors.toList());
        } else {
            returnList = Collections.emptyList();
        }

        return returnList;
    }

    /**
     * 转换成映射
     *
     * @param collection
     * @param keyMapper
     * @param <E>
     * @param <K>
     * @param <V>
     * @return
     */
    public static <E, K, V> Map<K, E> toMap(Collection<E> collection, Function<E, K> keyMapper) {
        QCheckUtil.notNull(keyMapper, "keyMapper null");

        return toMap(collection, keyMapper, Function.identity());
    }

    /**
     * 转换成映射
     *
     * @param collection
     * @param keyMapper
     * @param valueMapper
     * @param <E>
     * @param <K>
     * @param <V>
     * @return
     */
    public static <E, K, V> Map<K, V> toMap(Collection<E> collection, Function<E, K> keyMapper,
                                            Function<E, V> valueMapper) {
        QCheckUtil.notNull(keyMapper, "keyMapper null");
        QCheckUtil.notNull(valueMapper, "valueMapper null");
        Map<K, V> returnMap = null;

        if (collection != null) {
            returnMap = collection.stream().collect(Collectors.toMap(keyMapper, valueMapper, (k1, k2) -> k1));
        } else {
            returnMap = Collections.emptyMap();
        }

        return returnMap;
    }

    /**
     * 转换成列表
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(Collection<T> collection) {
        List<T> returnList = null;

        if (isNotEmpty(collection)) {
            if (collection instanceof List) {
                returnList = (List<T>) collection;
            } else {
                returnList = new ArrayList<>(collection);
            }
        } else {
            returnList = Collections.emptyList();
        }

        return returnList;
    }

    /**
     * 返回集合的第一个元素
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> T first(Collection<T> collection) {
        T returnItem = null;

        if (isNotEmpty(collection)) {
            if (collection instanceof List) {
                returnItem = ((List<T>) collection).get(0);
            } else {
                returnItem = collection.iterator().next();
            }
        }

        return returnItem;
    }

    /**
     * 对集合分组并返回
     *
     * @param collection
     * @param classifier
     * @param <E>
     * @param <K>
     * @return
     */
    public static <E, K> Map<K, List<E>> groupingBy(Collection<E> collection, Function<E, K> classifier) {
        QCheckUtil.notNull(classifier, "classifier null");
        Map<K, List<E>> returnMap = null;

        if (isNotEmpty(collection)) {
            returnMap = collection.stream().collect(Collectors.groupingBy(classifier));
        } else {
            returnMap = Collections.emptyMap();
        }

        return returnMap;
    }

}
