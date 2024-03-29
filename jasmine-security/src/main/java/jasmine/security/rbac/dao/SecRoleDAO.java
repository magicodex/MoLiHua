package jasmine.security.rbac.dao;

import jasmine.framework.database.mybatisplus.dao.BaseI18nEntityDAO;
import jasmine.security.rbac.mapper.SecRoleMapper;
import jasmine.security.rbac.model.SecRole;

import java.util.List;

/**
 * @author mh.z
 */

public class SecRoleDAO extends BaseI18nEntityDAO<SecRoleMapper, SecRole> {
    private SecRoleMapper baseMapper;

    public SecRoleDAO(SecRoleMapper baseMapper) {
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
