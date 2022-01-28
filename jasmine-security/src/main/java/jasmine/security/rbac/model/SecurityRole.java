package jasmine.security.rbac.model;

/**
 * <p>
 * 角色。
 * </p>
 *
 * @author mh.z
 */
public class SecurityRole {
    private Long id;
    /** 角色代码 */
    private String roleCode;
    /** 角色名称 */
    private String roleName;

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

}
