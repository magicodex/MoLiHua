package jasmine.security.rbac.model;

/**
 * <p>
 * 关联角色和功能。
 * </p>
 *
 * @author mh.z
 */
public class SecurityRoleFunction {
    private Long id;
    /** 角色ID */
    private Long roleId;
    /** 功能ID */
    private Long functionId;

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

}
