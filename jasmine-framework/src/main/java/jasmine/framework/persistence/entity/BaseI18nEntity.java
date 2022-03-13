package jasmine.framework.persistence.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @author mh.z
 */
public class BaseI18nEntity extends BaseEntity {
    /** 语言代码 */
    @TableField(value = "lang_code", fill = FieldFill.INSERT)
    private String langCode;

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

}
