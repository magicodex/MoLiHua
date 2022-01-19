package jasmine.framework.impl.context;

import jasmine.common.context.MessageTranslator;
import jasmine.common.util.QCheckUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author mh.z
 */
@Component
public class SpringMessageTranslator implements MessageTranslator {
    private final MessageSource messageSource;

    public SpringMessageTranslator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String translate(String source, Object... args) {
        QCheckUtil.notNull(source, "source null");
        String messageKey = source;

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
