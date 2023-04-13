package jasmine.framework.i18n.impl;

import jasmine.framework.i18n.I18nConstants;
import jasmine.framework.i18n.LocaleMessageProvider;
import jasmine.framework.common.util.CheckUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * @author mh.z
 */
public class DefaultLocaleMessageProvider implements LocaleMessageProvider {
    private final MessageSource messageSource;

    public DefaultLocaleMessageProvider(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String messageKey, Object... args) {
        CheckUtil.notNull(messageKey, "messageKey null");

        if (messageKey != null && messageKey.startsWith(I18nConstants.I18N_MESSAGE_KEY_PREFIX)) {
            messageKey = messageKey.substring(1);
        }

        Locale locale = LocaleContextHolder.getLocale();
        if (locale == null) {
            locale = Locale.SIMPLIFIED_CHINESE;
        }

        return messageSource.getMessage(messageKey, args, locale);
    }

    @Override
    public String getLanguage() {
        Locale locale = LocaleContextHolder.getLocale();

        if (locale != null) {
            return locale.toLanguageTag();
        }

        return Locale.SIMPLIFIED_CHINESE.toLanguageTag();
    }

}
