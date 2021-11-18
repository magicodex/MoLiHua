package jasmine.framework.impl.context;

import jasmine.common.context.MessageTranslator;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class SpringMessageTranslator implements MessageTranslator {
    private final MessageSource messageSource;

    public SpringMessageTranslator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String translate(String source, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();

        if (locale == null) {
            locale = Locale.SIMPLIFIED_CHINESE;
        }

        return messageSource.getMessage(source, args, locale);
    }

}
