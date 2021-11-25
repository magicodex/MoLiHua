package jasmine.common.context;

import jasmine.common.exception.UnexpectedException;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class I18N {
    /** 多语言翻译 */
    private static MessageTranslator translator;

    public I18N(MessageTranslator translator) {
        I18N.translator = translator;
    }

    public static void setTranslator(MessageTranslator translator) {
        I18N.translator = translator;
    }

    /**
     * 查找多语言并返回
     *
     * @param source
     * @param args
     * @return
     */
    public static String tr(String source, Object... args) {
        if (translator == null) {
            throw new UnexpectedException("I18N.translator null");
        }

        return translator.translate(source, args);
    }

}
