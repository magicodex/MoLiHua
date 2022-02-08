package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 权限。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_permission")
public class SecurityPermission {
    @TableId
    private Long id;

    /** 权限代码 */
    @TableField("permission_code")
    private String permissionCode;

    /** 权限名称 */
    @TableField("permission_name")
    private String permissionName;

    /** 访问类型 */
    @TableField("access_type")
    private String accessType;

    /** 请求方式 */
    @TableField("request_method")
    private String requestMethod;

    /** 请求资源 */
    @TableField("request_resource")
    private String requestResource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestResource() {
        return requestResource;
    }

    public void setRequestResource(String requestResource) {
        this.requestResource = requestResource;
    }

}
