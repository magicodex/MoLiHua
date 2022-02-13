package jasmine.core.util;

import cn.hutool.core.util.ObjectUtil;
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
     * 如果给定字符串为null返回默认值
     *
     * @param string
     * @param other
     * @return
     */
    public static String orElse(String string, String other) {
        return ObjectUtil.defaultIfNull(string, other);
    }

    /**
     * 如果给定字符串为null返回空字符串
     *
     * @param string
     * @return
     */
    public static String orEmpty(String string) {
        return ObjectUtil.defaultIfNull(string, "");
    }

}
