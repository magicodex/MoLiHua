package jasmine.framework.i18n;

/**
 * @author mh.z
 */
public interface I18nKeyAndArgs {

    /**
     * 返回多语言信息key
     *
     * @return
     */
    String getI18nKey();

    /**
     * 返回多语言信息参数
     *
     * @return
     */
    default Object[] getI18nArgs() {
        return new Object[0];
    }

}
