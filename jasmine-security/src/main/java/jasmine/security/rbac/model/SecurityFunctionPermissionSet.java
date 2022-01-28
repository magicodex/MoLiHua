package jasmine.security.rbac.model;

/**
 * <p>
 * 关联功能和权限集。
 * </p>
 *
 * @author mh.z
 */
public class SecurityFunctionPermissionSet {
    private Long id;
    /** 功能ID */
    private Long functionId;
    /** 权限集ID */
    private Long permissionSetId;

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

    public Long getPermissionSetId() {
        return permissionSetId;
    }

    public void setPermissionSetId(Long permissionSetId) {
        this.permissionSetId = permissionSetId;
    }

}
