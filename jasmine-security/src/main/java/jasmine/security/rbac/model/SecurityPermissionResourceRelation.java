package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 关联权限和资源。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_permission_resource_rel")
public class SecurityPermissionResourceRelation {
    @TableId
    private Long id;

    /** 权限ID */
    @TableField("permission_id")
    private Long permissionId;

    /** 资源ID */
    @TableField("resource_id")
    private Long resourceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

}
