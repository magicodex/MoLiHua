package jasmine.core.util;

import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollUtil;
import jasmine.core.util.sort.CollectionSortUtil;
import jasmine.core.util.sort.SortKey;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
     * 判断是否集合的数量相同
     *
     * @param collection1
     * @param collection2
     * @return
     */
    public static boolean isSizeEquals(@Nullable Collection<?> collection1,
                                       @Nullable Collection<?> collection2) {
        int size1 = (collection1 != null)
                ? collection1.size() : 0;
        int size2 = (collection2 != null)
                ? collection2.size() : 0;

        return (size1 == size2);
    }

    /**
     * 判断是否集合的数量不同
     *
     * @param collection1
     * @param collection2
     * @return
     */
    public static boolean isSizeNotEquals(@Nullable Collection<?> collection1,
                                          @Nullable Collection<?> collection2) {
        int size1 = (collection1 != null)
                ? collection1.size() : 0;
        int size2 = (collection2 != null)
                ? collection2.size() : 0;

        return (size1 != size2);
    }

    /**
     * 映射集合里的元素成其它类型的元素
     *
     * @param collection
     * @param function
     * @param <E>
     * @param <R>
     * @return
     */
    public static <E, R> List<R> mapToList(@Nullable Collection<E> collection,
                                           @Nonnull Function<E, R> function) {
        QCheckUtil.notNull(function, "function null");

        if (isEmpty(collection)) {
            return Collections.emptyList();
        }

        List<R> returnList = collection.stream()
                .map(function).collect(Collectors.toList());

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
    public static <T> List<T> chooseToList(@Nullable Collection<T> collection,
                                           @Nonnull Predicate<T> predicate) {
        QCheckUtil.notNull(predicate, "predicate null");

        if (isEmpty(collection)) {
            return Collections.emptyList();
        }

        List<T> returnList = collection.stream()
                .filter(predicate).collect(Collectors.toList());

        return returnList;
    }

    /**
     * 转换成列表
     *
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(@Nullable Collection<T> collection) {
        if (isEmpty(collection)) {
            return Collections.emptyList();
        }

        List<T> returnList = null;
        if (collection instanceof List) {
            returnList = (List<T>) collection;
        } else {
            returnList = new ArrayList<>(collection);
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
     * @return
     */
    public static <E, K> Map<K, E> toMap(@Nullable Collection<E> collection,
                                         @Nonnull Function<E, K> keyMapper) {
        QCheckUtil.notNull(keyMapper, "keyMapper null");

        if (isEmpty(collection)) {
            return Collections.emptyMap();
        }

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
    public static <E, K, V> Map<K, V> toMap(@Nullable Collection<E> collection,
                                            @Nonnull Function<E, K> keyMapper,
                                            @Nonnull Function<E, V> valueMapper) {
        QCheckUtil.notNull(keyMapper, "keyMapper null");
        QCheckUtil.notNull(valueMapper, "valueMapper null");

        if (isEmpty(collection)) {
            return Collections.emptyMap();
        }

        Map<K, V> returnMap = collection.stream()
                .collect(Collectors.toMap(keyMapper, valueMapper, (k1, k2) -> k1));

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
    public static <E, K> Map<K, List<E>> groupBy(@Nullable Collection<E> collection,
                                                 @Nonnull Function<E, K> classifier) {
        QCheckUtil.notNull(classifier, "classifier null");

        if (isEmpty(collection)) {
            return Collections.emptyMap();
        }

        return CollStreamUtil.groupByKey(collection, classifier);
    }

    /**
     * 排序
     *
     * @param collection
     * @param keys
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K extends Comparable> List<T> sortBy(@Nonnull Collection<T> collection,
                                                           @Nonnull SortKey<T, K>... keys) {
        return CollectionSortUtil.sort(collection, keys);
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
    public static <E, R> List<R> forEach(@Nullable Collection<E> collection,
                                         @Nonnull Function<E, R> function) {
        QCheckUtil.notNull(function, "function null");

        if (isEmpty(collection)) {
            return Collections.emptyList();
        }

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
    public static <E> void forEach(@Nullable Collection<E> collection,
                                   @Nonnull java.util.function.Consumer<E> consumer) {
        QCheckUtil.notNull(consumer, "consumer null");

        if (isEmpty(collection)) {
            return;
        }

        collection.forEach((current) -> {
            consumer.accept(current);
        });
    }

}
