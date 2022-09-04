package jasmine.core.util;

import jasmine.core.exception.InvalidPropertyException;
import jasmine.core.i18n.LocaleMessageProvider;

/**
 * <p>
 * 多语言工具类。
 * </p>
 *
 * @author mh.z
 */
public class QI18nUtil {
    private static LocaleMessageProvider provider;

    public static void initUtil(LocaleMessageProvider provider) {
        QI18nUtil.provider = provider;
    }

    public static LocaleMessageProvider getProvider() {
        return provider;
    }

    /**
     * 查找多语言并返回
     *
     * @param messageKey
     * @param args
     * @return
     */
    public static String getMessage(String messageKey, Object... args) {
        if (provider == null) {
            throw new InvalidPropertyException("QI18nUtil.provider null");
        }

        return provider.getMessage(messageKey, args);
    }

    /**
     * 返回语言代码
     *
     * @return
     */
    public static String getLanguage() {
        return provider.getLanguage();
    }

}
