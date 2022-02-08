package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 关联角色和功能。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_role_function")
public class SecurityRoleFunction {
    @TableId
    private Long id;

    /** 角色ID */
    @TableField("role_id")
    private Long roleId;

    /** 功能ID */
    @TableField("function_id")
    private Long functionId;

    /** 租户ID */
    @TableField("tenant_id")
    private Long tenantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
