package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 菜单。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_menu")
public class SecurityMenu {
    @TableId
    private Long id;

    /** 模板ID */
    @TableField("template_id")
    private Long templateId;

    /** 父菜单ID */
    @TableField(value = "parent_id", updateStrategy = FieldStrategy.IGNORED)
    private Long parentId;

    /** 菜单代码 */
    @TableField("menu_code")
    private String menuCode;

    /** 菜单名称 */
    @TableField("menu_name")
    private String menuName;

    /** 菜单序号 */
    @TableField("menu_order")
    private Integer menuOrder;

    /** 页面 */
    @TableField("target_page")
    private String targetPage;

    /** 租户ID */
    @TableField("tenant_id")
    private Long tenantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getTargetPage() {
        return targetPage;
    }

    public void setTargetPage(String targetPage) {
        this.targetPage = targetPage;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

}
