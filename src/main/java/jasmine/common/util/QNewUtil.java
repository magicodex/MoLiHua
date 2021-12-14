package jasmine.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mh.z
 */
public class QNewUtil {

    /**
     * 创建 Map 对象并返回
     *
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> map() {
        return new HashMap<>(16);
    }

}
