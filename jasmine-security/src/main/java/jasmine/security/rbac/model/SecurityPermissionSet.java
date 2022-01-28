package jasmine.security.rbac.model;

/**
 * @author mh.z
 */
public class SecurityPermissionSet {

    private Long id;

    private String permissionSetCode;

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
