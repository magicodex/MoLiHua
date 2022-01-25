package jasmine.framework.i18n;

import jasmine.framework.util.QCheckUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author mh.z
 */
@Component
public class LocalizationMessageProviderBean implements LocalizationMessageProvider {
    private final MessageSource messageSource;

    public LocalizationMessageProviderBean(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String messageKey, Object... args) {
        QCheckUtil.notNull(messageKey, "messageKey null");

        if (messageKey != null && messageKey.startsWith("$")) {
            messageKey = messageKey.substring(1);
        }

        Locale locale = LocaleContextHolder.getLocale();
        if (locale == null) {
            locale = Locale.SIMPLIFIED_CHINESE;
        }

        return messageSource.getMessage(messageKey, args, locale);
    }

}
