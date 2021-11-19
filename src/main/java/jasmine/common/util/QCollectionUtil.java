package jasmine.common.util;

import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollUtil;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class QCollectionUtil extends CollUtil {

    public static <E, T> List<T> toList(Collection<E> collection, Function<E, T> function) {
        return CollStreamUtil.toList(collection, function);
    }

}
