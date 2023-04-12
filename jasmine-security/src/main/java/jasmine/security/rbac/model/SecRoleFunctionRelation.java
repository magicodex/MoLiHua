package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.database.mybatisplus.entity.BaseEntity;

/**
 * <p>
 * 关联角色和功能。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_role_function_rel")
public class SecRoleFunctionRelation extends BaseEntity {

    /** 角色ID */
    @TableField("role_id")
    private Long roleId;

    /** 功能ID */
    @TableField("function_id")
    private Long functionId;

    /** 租户ID */
    @TableField("tenant_id")
    private Long tenantId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
