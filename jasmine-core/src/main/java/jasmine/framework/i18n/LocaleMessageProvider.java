package jasmine.framework.i18n;

/**
 * <p>
 * 本地化信息提供者。
 * </p>
 *
 * @author mh.z
 */
public interface LocaleMessageProvider {

    /**
     * 查找信息
     *
     * @param messageKey
     * @param args
     * @return
     */
    String getMessage(String messageKey, Object... args);

    /**
     * 返回语言代码
     *
     * @return
     */
    String getLanguage();
}
