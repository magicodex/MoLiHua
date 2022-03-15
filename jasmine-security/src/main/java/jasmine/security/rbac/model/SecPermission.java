package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.persistence.annotation.I18n;
import jasmine.framework.persistence.entity.BaseI18nEntity;

/**
 * <p>
 * 权限。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_permission")
public class SecPermission extends BaseI18nEntity {

    /** 权限代码 */
    @TableField("permission_code")
    private String permissionCode;

    /** 权限名称 */
    @I18n
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
