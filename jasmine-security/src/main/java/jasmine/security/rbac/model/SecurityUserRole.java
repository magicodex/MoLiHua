package jasmine.security.rbac.model;

/**
 * <p>
 * 关联用户和角色。
 * </p>
 *
 * @author mh.z
 */
public class SecurityUserRole {
    private Long id;
    /** 用户ID */
    private Long userId;
    /** 角色ID */
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
