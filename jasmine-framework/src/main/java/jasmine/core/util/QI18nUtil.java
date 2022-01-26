package jasmine.core.util;

import jasmine.core.i18n.LocaleMessageProvider;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class QI18nUtil {
    private static LocaleMessageProvider provider;

    public QI18nUtil(LocaleMessageProvider provider) {
        QI18nUtil.provider = provider;
    }

    public static void setProvider(LocaleMessageProvider provider) {
        QI18nUtil.provider = provider;
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
            throw new RuntimeException("QI18nUtil.provider null");
        }

        return provider.getMessage(messageKey, args);
    }

}
