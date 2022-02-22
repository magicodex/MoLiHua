package jasmine.security.authorization.rbac;

import jasmine.core.util.QObjectUtil;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author mh.z
 */
public class RoleAuthority implements GrantedAuthority {
    private String authority;
    /** 角色ID */
    private Long roleId;
    /** 角色代码 */
    private String roleCode;

    public RoleAuthority(Long roleId, String roleCode) {
        this.roleId = roleId;
        this.roleCode = roleCode;
        this.authority = QObjectUtil.defaultIfNull(roleId, "") + ":" + roleCode;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

}
