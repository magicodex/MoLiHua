package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.database.mybatisplus.annotation.I18n;
import jasmine.framework.database.mybatisplus.entity.BaseI18nEntity;

/**
 * <p>
 * 菜单模板。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_menu_template")
public class SecMenuTemplate extends BaseI18nEntity {

    /** 模板代码 */
    @TableField("template_code")
    private String templateCode;

    /** 模板名称 */
    @I18n
    @TableField("template_name")
    private String templateName;

    /** 备注 */
    @I18n
    @TableField(value = "remark", updateStrategy = FieldStrategy.IGNORED)
    private String remark;

    /** 租户ID */
    @TableField("tenant_id")
    private Long tenantId;

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

}
