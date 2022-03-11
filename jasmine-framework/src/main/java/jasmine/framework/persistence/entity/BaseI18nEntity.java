package jasmine.framework.persistence.entity;

/**
 * @author mh.z
 */
public class BaseI18nEntity extends BaseEntity {
    /** 语言代码 */
    private String langCode;

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

}
