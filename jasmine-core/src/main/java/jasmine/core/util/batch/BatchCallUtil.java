package jasmine.core.util.batch;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollUtil;

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
        QCheckUtil.notNull(collection, "collection null");
        QCheckUtil.notNull(function, "function null");
        List<T> list = QCollUtil.toList(collection);

        if (collection.size() > batchSize) {
            List<List<T>> partList = QCollUtil.splitList(list, batchSize);

            partList.forEach((part) -> {
                function.accept(part);
            });
        } else {
            function.accept(list);
        }
    }

}
