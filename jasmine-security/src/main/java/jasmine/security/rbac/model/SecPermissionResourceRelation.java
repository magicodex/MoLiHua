package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.mybatis.entity.BaseEntity;

/**
 * <p>
 * 关联权限和资源。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_permission_resource_rel")
public class SecPermissionResourceRelation extends BaseEntity {

    /** 权限ID */
    @TableField("permission_id")
    private Long permissionId;

    /** 资源ID */
    @TableField("resource_id")
    private Long resourceId;

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
