package jasmine.framework.common.util.batch;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.CollectionUtil;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author mh.z
 */
public class BatchCallUtil {

    /**
     * 调用
     *
     * @param collection
     * @param batchSize
     * @param function
     * @param <T>
     */
    public static <T> void call(Collection<T> collection, int batchSize,
                                Consumer<List<T>> function) {
        CheckUtil.notNull(collection, "collection null");
        CheckUtil.notNull(function, "function null");
        List<T> list = CollectionUtil.toList(collection);

        if (collection.size() > batchSize) {
            List<List<T>> partList = CollectionUtil.splitList(list, batchSize);

            partList.forEach((part) -> {
                function.accept(part);
            });
        } else {
            function.accept(list);
        }
    }

}
