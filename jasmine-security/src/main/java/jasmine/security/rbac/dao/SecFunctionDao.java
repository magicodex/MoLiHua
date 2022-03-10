package jasmine.security.rbac.dao;

import jasmine.core.util.QCollectionUtil;
import jasmine.security.rbac.dto.SecFunctionBaseInfoDTO;
import jasmine.security.rbac.mapper.SecFunctionMapper;

import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */

public class SecFunctionDao {
    private SecFunctionMapper baseMapper;

    public SecFunctionDao(SecFunctionMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 查找指定角色被授予的所有功能
     *
     * @param roleIds
     * @return
     */
    public List<SecFunctionBaseInfoDTO> listAllTenantFunctionBaseInfoDTOsByRoleIds(List<Long> roleIds) {
        if (QCollectionUtil.isEmpty(roleIds)) {
            return Collections.emptyList();
        }

        return baseMapper.listAllTenantFunctionBaseInfoDTOsByRoleIds(roleIds);
    }

}
