package jasmine.security.rbac.model;

/**
 * <p>
 * 权限。
 * </p>
 *
 * @author mh.z
 */
public class SecurityPermission {
    private Long id;
    /** 请求方式 */
    private String methodType;
    /** 权限代码 */
    private String permissionCode;
    /** 权限名称 */
    private String permissionName;
    /** 资源路径 */
    private String resourcePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

}
