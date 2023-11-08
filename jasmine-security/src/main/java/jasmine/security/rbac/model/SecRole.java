package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.database.mybatisplus.annotation.I18n;
import jasmine.framework.database.mybatisplus.entity.BaseI18nEntity;

/**
 * <p>
 * 角色。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_role")
public class SecRole extends BaseI18nEntity {

    /** 角色代码 */
    @TableField("role_code")
    private String roleCode;

    /** 角色名称 */
    @I18n
    @TableField("role_name")
    private String roleName;

    /** 生效标志 */
    @TableField("effective_flag")
    private Boolean effectiveFlag;

    /** 备注 */
    @I18n
    @TableField("remark")
    private String remark;

    /** 租户ID */
    @TableField("tenant_id")
    private Long tenantId;

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

    public Boolean getEffectiveFlag() {
        return effectiveFlag;
    }

    public void setEffectiveFlag(Boolean effectiveFlag) {
        this.effectiveFlag = effectiveFlag;
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
