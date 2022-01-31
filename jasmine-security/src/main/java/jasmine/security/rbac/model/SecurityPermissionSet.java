package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 权限集。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_permission_set")
public class SecurityPermissionSet {
    @TableId
    private Long id;

    /** 权限集代码 */
    @TableField("permissionSet_code")
    private String permissionSetCode;

    /** 权限集名称 */
    @TableField("permission_set_name")
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
