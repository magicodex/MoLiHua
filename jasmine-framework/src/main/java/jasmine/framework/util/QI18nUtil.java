package jasmine.framework.util;

import jasmine.framework.i18n.LocalizationMessageProvider;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class QI18nUtil {
    private static LocalizationMessageProvider provider;

    public QI18nUtil(LocalizationMessageProvider provider) {
        QI18nUtil.provider = provider;
    }

    public static void setProvider(LocalizationMessageProvider provider) {
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
