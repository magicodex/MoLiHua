package jasmine.core.util.batch;

import jasmine.core.util.CheckUtil;
import jasmine.core.util.CollUtil;

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
        List<T> list = CollUtil.toList(collection);

        if (collection.size() > batchSize) {
            List<List<T>> partList = CollUtil.splitList(list, batchSize);

            partList.forEach((part) -> {
                function.accept(part);
            });
        } else {
            function.accept(list);
        }
    }

}
