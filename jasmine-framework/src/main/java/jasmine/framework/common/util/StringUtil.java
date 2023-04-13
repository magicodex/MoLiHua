package jasmine.framework.common.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import javax.annotation.Nullable;

/**
 * <p>
 * 字符串工具类。
 * </p>
 *
 * @author mh.z
 */
public class StringUtil extends StrUtil {

    /**
     * 转换成字符串
     *
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        return (obj == null) ? null : obj.toString();
    }

    /**
     * 如果给定对象为null返回默认值
     *
     * @param content
     * @param other
     * @return
     */
    public static String orElse(@Nullable Object content, String other) {
        if (content != null) {
            content = content.toString();
        }

        return ObjectUtil.defaultIfNull((String) content, other);
    }

    /**
     * 如果给定对象为null返回空字符串
     *
     * @param content
     * @return
     */
    public static String orEmpty(@Nullable Object content) {
        if (content != null) {
            content = content.toString();
        }

        return ObjectUtil.defaultIfNull((String) content, "");
    }

}
