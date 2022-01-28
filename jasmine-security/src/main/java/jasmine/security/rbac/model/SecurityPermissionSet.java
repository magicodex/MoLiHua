package jasmine.security.rbac.model;

/**
 * <p>
 * 权限集。
 * </p>
 *
 * @author mh.z
 */
public class SecurityPermissionSet {
    private Long id;
    /** 权限集代码 */
    private String permissionSetCode;
    /** 权限集名称 */
    private String permissionSetName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionSetCode() {
        return permissionSetCode;
    }

    public void setPermissionSetCode(String permissionSetCode) {
        this.permissionSetCode = permissionSetCode;
    }

    public String getPermissionSetName() {
        return permissionSetName;
    }

    public void setPermissionSetName(String permissionSetName) {
        this.permissionSetName = permissionSetName;
    }

}
