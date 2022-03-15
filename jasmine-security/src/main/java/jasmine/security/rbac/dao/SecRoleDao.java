package jasmine.security.rbac.dao;

import jasmine.security.rbac.mapper.SecRoleMapper;
import jasmine.security.rbac.model.SecRole;

import java.util.List;

/**
 * @author mh.z
 */

public class SecRoleDao {
    private SecRoleMapper baseMapper;

    public SecRoleDao(SecRoleMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 查找指定用户被授予的所有角色
     *
     * @param userId
     * @return
     */
    public List<SecRole> listAllTenantRolesByUserIdNoI18n(Long userId) {
        return baseMapper.listAllTenantRolesByUserIdNoI18n(userId);
    }

}
