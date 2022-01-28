package jasmine.security.rbac.model;

/**
 * <p>
 * 关联权限集和权限。
 * </p>
 *
 * @author mh.z
 */
public class SecurityPermissionSetPermission {
    private Long id;
    /** 权限集ID */
    private Long permissionSetId;
    /** 权限ID */
    private Long permissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPermissionSetId() {
        return permissionSetId;
    }

    public void setPermissionSetId(Long permissionSetId) {
        this.permissionSetId = permissionSetId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

}
