package jasmine.security.rbac.service;

import jasmine.security.rbac.mapper.SecurityRoleMapper;
import jasmine.security.rbac.model.SecurityRole;

import java.util.List;

/**
 * @author mh.z
 */

public class SecurityRoleService {
    private SecurityRoleMapper baseMapper;

    public SecurityRoleService(SecurityRoleMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 查找指定用户被授予的所有角色
     *
     * @param userId
     * @return
     */
    public List<SecurityRole> listRolesByUserId(Long userId) {
        return baseMapper.listRolesByUserId(userId);
    }

}
