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

}
