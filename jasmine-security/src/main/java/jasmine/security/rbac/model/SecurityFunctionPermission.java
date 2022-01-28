package jasmine.security.rbac.model;

/**
 * <p>
 * 关联功能和权限。
 * </p>
 *
 * @author mh.z
 */
public class SecurityFunctionPermission {
    private Long id;
    /** 功能ID */
    private Long functionId;
    /** 权限ID */
    private Long permissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

}
