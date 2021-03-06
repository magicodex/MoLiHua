package jasmine.demo.util;

import jasmine.core.util.QI18nUtil;
import jasmine.core.util.QNewUtil;

import java.util.List;
import java.util.Map;

/**
 * @author mh.z
 */
public class Q {

    /**
     * 获取多语言
     *
     * @param messageKey
     * @param args
     * @return
     */
    public static String tr(String messageKey, Object... args) {
        return QI18nUtil.getMessage(messageKey, args);
    }

    /**
     * 创建 Map 对象
     *
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> map() {
        return QNewUtil.map();
    }

    /**
     * 创建 List 对象
     *
     * @param <T>
     * @return
     */
    public static <T> List<T> list() {
        return QNewUtil.list();
    }

}
