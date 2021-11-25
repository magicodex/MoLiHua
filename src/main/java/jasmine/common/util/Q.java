package jasmine.common.util;

import jasmine.common.context.I18N;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 定义常用的工具方法。
 * </p>
 *
 * @author mh.z
 */
public class Q {

    /**
     * 返回多语言信息
     *
     * @param source
     * @param args
     * @return
     */
    public static String tr(String source, Object... args) {
        return I18N.tr(source, args);
    }

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
