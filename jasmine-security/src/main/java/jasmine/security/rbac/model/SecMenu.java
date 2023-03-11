package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.mybatis.annotation.I18n;
import jasmine.mybatis.entity.BaseI18nEntity;

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

    /** 菜单序号 */
    @TableField("menu_order")
    private Integer menuOrder;

    /** 链接资源ID */
    @TableField("link_resource_id")
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

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
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
