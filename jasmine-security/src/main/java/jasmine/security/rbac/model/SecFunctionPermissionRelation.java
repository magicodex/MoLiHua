package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.mybatis.entity.BaseEntity;

/**
 * <p>
 * 关联功能和权限。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_function_permission_rel")
public class SecFunctionPermissionRelation extends BaseEntity {

    /** 功能ID */
    @TableField("function_id")
    private Long functionId;

    /** 权限ID */
    @TableField("permission_id")
    private Long permissionId;

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

}
