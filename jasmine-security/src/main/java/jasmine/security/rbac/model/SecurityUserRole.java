package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 关联用户和角色。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_user_role")
public class SecurityUserRole {
    @TableId
    private Long id;
    /** 用户ID */
    private Long userId;
    /** 角色ID */
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
