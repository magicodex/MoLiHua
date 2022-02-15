package jasmine.framework.i18n;

import jasmine.core.i18n.LocaleMessageProvider;
import jasmine.core.util.QCheckUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author mh.z
 */
@Component
public class DefaultLocaleMessageProvider implements LocaleMessageProvider {
    private final MessageSource messageSource;
    /** 多语言key前缀 */
    private static final String MESSAGE_KEY_PREFIX = "$";

    public DefaultLocaleMessageProvider(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String messageKey, Object... args) {
        QCheckUtil.notNull(messageKey, "messageKey null");

        if (messageKey != null && messageKey.startsWith(MESSAGE_KEY_PREFIX)) {
            messageKey = messageKey.substring(1);
        }

        Locale locale = LocaleContextHolder.getLocale();
        if (locale == null) {
            locale = Locale.SIMPLIFIED_CHINESE;
        }

        return messageSource.getMessage(messageKey, args, locale);
    }

}
