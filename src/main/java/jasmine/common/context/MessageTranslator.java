package jasmine.common.context;

/**
 * <p>
 * 多语言翻译。
 * </p>
 *
 * @author mh.z
 */
public interface MessageTranslator {

    /**
     * 翻译
     *
     * @param source
     * @param args
     * @return
     */
    String translate(String source, Object... args);
}
