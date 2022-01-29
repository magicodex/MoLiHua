package jasmine.security.rbac.model;

/**
 * <p>
 * 菜单模板。
 * </p>
 *
 * @author mh.z
 */
public class SecurityMenuTemplate {
    private Long id;
    /** 模板代码 */
    private String templateCode;
    /** 模板名称 */
    private String templateName;

    /** 租户ID */
    private Long tenantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
