package jasmine.framework.common.util.sort;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.CollectionUtil;
import jasmine.framework.common.constant.NumberConstants;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mh.z
 */
public class CollectionSortUtil {

    /**
     * 排序
     *
     * @param collection
     * @param keys
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K extends Comparable> List<T> sort(@Nonnull Collection<T> collection,
                                                         @Nonnull SortKey<T, K>... keys) {
        CheckUtil.notNull(collection, "collection null");
        CheckUtil.notNull(keys, "keys null");
        Comparator<T> comparator = null;

        if (keys.length == NumberConstants.NUMBER_0) {
            return CollectionUtil.toList(collection);
        }

        for (SortKey<T, K> key : keys) {
            Comparator<T> newComparator = new KeyComparator<>(key.getKeyFunction(), key.getAsc());

            if (comparator != null) {
                comparator = comparator.thenComparing(newComparator);
            } else {
                comparator = newComparator;
            }
        }

        List<T> sortedList = collection.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        return sortedList;
    }

}
