package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 关联权限集和权限。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_permission_set_permission")
public class SecurityPermissionSetPermission {
    @TableId
    private Long id;

    /** 权限集ID */
    @TableField("permission_set_id")
    private Long permissionSetId;

    /** 权限ID */
    @TableField("permission_id")
    private Long permissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPermissionSetId() {
        return permissionSetId;
    }

    public void setPermissionSetId(Long permissionSetId) {
        this.permissionSetId = permissionSetId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

}
