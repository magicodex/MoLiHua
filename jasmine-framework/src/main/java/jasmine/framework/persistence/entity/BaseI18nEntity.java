package jasmine.framework.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @author mh.z
 */
public class BaseI18nEntity extends BaseEntity {
    /** 语言代码 */
    @TableField(value = "created_lang")
    private String createdLang;

    public String getCreatedLang() {
        return createdLang;
    }

    public void setCreatedLang(String createdLang) {
        this.createdLang = createdLang;
    }

}
