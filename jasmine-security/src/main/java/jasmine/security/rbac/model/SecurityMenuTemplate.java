package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.persistence.entity.BaseEntity;

/**
 * <p>
 * 菜单模板。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_menu_template")
public class SecurityMenuTemplate extends BaseEntity {

    /** 模板代码 */
    @TableField("template_code")
    private String templateCode;

    /** 模板名称 */
    @TableField("template_name")
    private String templateName;

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

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

}
