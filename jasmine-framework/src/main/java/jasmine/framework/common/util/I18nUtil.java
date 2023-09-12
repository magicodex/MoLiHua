package jasmine.framework.common.util;

import jasmine.framework.common.exception.InvalidPropertyException;
import jasmine.framework.context.WithContext;
import jasmine.framework.i18n.LocaleMessageProvider;

/**
 * <p>
 * 多语言工具类。
 * </p>
 *
 * @author mh.z
 */
public class I18nUtil implements WithContext {
    private static LocaleMessageProvider provider;

    public static void initUtil(LocaleMessageProvider provider) {
        I18nUtil.provider = provider;
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
            throw new InvalidPropertyException("I18nUtil.provider null", null);
        }

        return provider.getMessage(messageKey, args);
    }

    /**
     * 查找多语言并返回
     *
     * @param messageKey
     * @param args
     * @return
     */
    public static String getMessageAllowNone(String messageKey, Object... args) {
        if (provider == null) {
            throw new InvalidPropertyException("I18nUtil.provider null", null);
        }

        return provider.getMessageAllowNone(messageKey, args);
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
