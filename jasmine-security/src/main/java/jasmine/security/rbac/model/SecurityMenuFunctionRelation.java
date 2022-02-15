package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.persistence.entity.BaseEntity;

/**
 * <p>
 * 关联菜单和功能。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_menu_function_rel")
public class SecurityMenuFunctionRelation extends BaseEntity {

    /** 菜单ID */
    @TableField("menu_id")
    private Long menuId;

    /** 功能ID */
    @TableField("function_id")
    private Long functionId;

    /** 租户ID */
    @TableField("tenant_id")
    private Long tenantId;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

}
