package jasmine.core.util;

import cn.hutool.core.util.ObjectUtil;

/**
 * @author mh.z
 */
public class QObjectUtil extends ObjectUtil {

    /**
     * 解析成 Long 类型
     *
     * @param object
     * @return
     */
    public static Long parseLong(Object object) {
        Long returnValue = null;

        if (object != null) {
            if (object instanceof Number) {
                returnValue = ((Number) object).longValue();
            } else {
                String string = object.toString();

                if (QStringUtil.isNotBlank(string)) {
                    returnValue = Long.parseLong(string);
                }
            }
        }

        return returnValue;
    }

    /**
     * 解析成 Integer 类型
     *
     * @param object
     * @return
     */
    public static Integer parseInteger(Object object) {
        Integer returnValue = null;

        if (object != null) {
            if (object instanceof Number) {
                returnValue = ((Number) object).intValue();
            } else {
                String string = object.toString();

                if (QStringUtil.isNotBlank(string)) {
                    returnValue = Integer.parseInt(string);
                }
            }
        }

        return returnValue;
    }

    /**
     * 解析成 Boolean 类型
     *
     * @param object
     * @return
     */
    public static Boolean parseBoolean(Object object) {
        Boolean returnValue = null;

        if (object != null) {
            if (object instanceof Boolean) {
                returnValue = (Boolean) object;
            } else {
                String string = object.toString();

                if (QStringUtil.isNotBlank(string)) {
                    returnValue = Boolean.parseBoolean(string);
                }
            }
        }

        return returnValue;
    }

}
