package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.database.mybatisplus.annotation.I18n;
import jasmine.framework.database.mybatisplus.entity.BaseI18nEntity;

/**
 * <p>
 * 菜单。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_menu")
public class SecMenu extends BaseI18nEntity {

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
    @I18n
    @TableField("menu_name")
    private String menuName;

    /** 菜单图标 */
    @TableField(value = "menu_icon", updateStrategy = FieldStrategy.IGNORED)
    private String menuIcon;

    /** 菜单路径 */
    @TableField(value = "menu_path", updateStrategy = FieldStrategy.IGNORED)
    private String menuPath;

    /** 菜单序号 */
    @TableField("menu_order")
    private Integer menuOrder;

    /** 链接功能ID */
    @TableField(value = "link_function_id", updateStrategy = FieldStrategy.IGNORED)
    private Long linkFunctionId;

    /** 链接资源ID */
    @TableField(value = "link_resource_id", updateStrategy = FieldStrategy.IGNORED)
    private Long linkResourceId;

    /** 租户ID */
    @TableField("tenant_id")
    private Long tenantId;

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

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public Long getLinkFunctionId() {
        return linkFunctionId;
    }

    public void setLinkFunctionId(Long linkFunctionId) {
        this.linkFunctionId = linkFunctionId;
    }

    public Long getLinkResourceId() {
        return linkResourceId;
    }

    public void setLinkResourceId(Long linkResourceId) {
        this.linkResourceId = linkResourceId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

}
