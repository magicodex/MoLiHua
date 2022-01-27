package jasmine.core.util;

import cn.hutool.core.util.StrUtil;

/**
 * <p>
 * 字符串工具类。
 * </p>
 *
 * @author mh.z
 */
public class QStringUtil extends StrUtil {

    /**
     * 转换成字符串
     *
     * @param object
     * @return
     */
    public static String toString(Object object) {
        if (object != null) {
            return object.toString();
        }

        return null;
    }

    /**
     * 若第一个参数是null则返回第二个参数
     *
     * @param object
     * @param other
     * @return
     */
    public static String orElse(Object object, String other) {
        if (object != null) {
            return object.toString();
        }

        return other;
    }

}
