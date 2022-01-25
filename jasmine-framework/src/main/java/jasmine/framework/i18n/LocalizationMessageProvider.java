package jasmine.framework.i18n;

/**
 * <p>
 * 本地化信息提供者。
 * </p>
 *
 * @author mh.z
 */
public interface LocalizationMessageProvider {

    /**
     * 查找信息
     *
     * @param messageKey
     * @param args
     * @return
     */
    String getMessage(String messageKey, Object... args);
}
