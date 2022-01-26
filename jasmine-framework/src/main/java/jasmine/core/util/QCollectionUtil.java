package jasmine.core.util;

import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollUtil;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

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
     * @param <T>
     * @return
     */
    public static <E, T> List<T> toList(Collection<E> collection, Function<E, T> function) {
        return CollStreamUtil.toList(collection, function);
    }

}
