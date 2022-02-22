package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.persistence.entity.BaseEntity;

/**
 * <p>
 * 权限。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_permission")
public class SecPermission extends BaseEntity {

    /** 权限代码 */
    @TableField("permission_code")
    private String permissionCode;

    /** 权限名称 */
    @TableField("permission_name")
    private String permissionName;

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
