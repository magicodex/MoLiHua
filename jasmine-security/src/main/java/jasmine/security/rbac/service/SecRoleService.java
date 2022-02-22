package jasmine.security.rbac.service;

import jasmine.security.rbac.mapper.SecRoleMapper;
import jasmine.security.rbac.model.SecRole;

import java.util.List;

/**
 * @author mh.z
 */

public class SecRoleService {
    private SecRoleMapper baseMapper;

    public SecRoleService(SecRoleMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 查找指定用户被授予的所有角色
     *
     * @param userId
     * @return
     */
    public List<SecRole> listRolesByUserId(Long userId) {
        return baseMapper.listRolesByUserId(userId);
    }

}
