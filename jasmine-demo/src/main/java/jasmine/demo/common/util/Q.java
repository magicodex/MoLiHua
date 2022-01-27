package jasmine.demo.common.util;

import jasmine.core.util.QI18nUtil;
import jasmine.core.util.QNewUtil;

import java.util.Map;

/**
 * @author mh.z
 */
public class Q {

    /**
     * 查找多语言信息并返回
     *
     * @param messageKey
     * @param args
     * @return
     */
    public String tr(String messageKey, Object... args) {
        return QI18nUtil.getMessage(messageKey, args);
    }

    /**
     * 创建映射对象并返回
     *
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> Map<K, V> map() {
        return QNewUtil.map();
    }

}
