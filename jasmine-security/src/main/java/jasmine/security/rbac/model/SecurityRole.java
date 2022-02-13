package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 角色。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_role")
public class SecurityRole {
    @TableId
    private Long id;

    /** 角色代码 */
    @TableField("role_code")
    private String roleCode;

    /** 角色名称 */
    @TableField("role_name")
    private String roleName;

    /** 启用标志 */
    @TableField("enable_flag")
    private Boolean enableFlag;

    /** 备注 */
    @TableField("remark")
    private String remark;

    /** 租户ID */
    @TableField("tenant_id")
    private Long tenantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Boolean enableFlag) {
        this.enableFlag = enableFlag;
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
