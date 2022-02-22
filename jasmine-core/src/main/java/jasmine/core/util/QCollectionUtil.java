package jasmine.core.util;

import cn.hutool.core.collection.CollStreamUtil;
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
    public static <E, K, V> Map<K, E> toIdentityMap(Collection<E> collection, Function<E, K> keyMapper) {
        return CollStreamUtil.toIdentityMap(collection, keyMapper);
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
     * 对集合分组并返回
     *
     * @param collection
     * @param classifier
     * @param <E>
     * @param <K>
     * @return
     */
    public static <E, K> Map<K, List<E>> groupByKey(Collection<E> collection, Function<E, K> classifier) {
        return CollStreamUtil.groupByKey(collection, classifier);
    }

    /**
     * 遍历集合
     *
     * @param collection
     * @param function
     * @param <E>
     * @param <R>
     * @return
     */
    public static <E, R> List<R> forEach(Collection<E> collection, Function<E, R> function) {
        QCheckUtil.notNull(collection, "collection null");
        QCheckUtil.notNull(function, "function null");
        List<R> returnList = new ArrayList<>(collection.size());

        collection.forEach((current) -> {
            returnList.add(function.apply(current));
        });

        return returnList;
    }

    /**
     * 遍历集合
     *
     * @param collection
     * @param consumer
     * @param <E>
     */
    public static <E> void forEach(Collection<E> collection, java.util.function.Consumer<E> consumer) {
        QCheckUtil.notNull(collection, "collection null");
        QCheckUtil.notNull(consumer, "consumer null");

        collection.forEach((current) -> {
            consumer.accept(current);
        });
    }

}
