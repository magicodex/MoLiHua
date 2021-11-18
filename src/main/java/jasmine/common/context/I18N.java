package jasmine.common.context;

import jasmine.common.exception.UnexpectedException;
import org.springframework.stereotype.Component;

@Component
public class I18N {
    private static MessageTranslator translator;

    public I18N(MessageTranslator translator) {
        I18N.translator = translator;
    }

    public static void setTranslator(MessageTranslator translator) {
        I18N.translator = translator;
    }

    public static String tr(String source, Object... args) {
        if (translator == null) {
            throw new UnexpectedException("I18N.translator null");
        }

        return translator.translate(source, args);
    }

}
