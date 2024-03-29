package jasmine.security.rbac.dao;

import jasmine.framework.common.util.CollectionUtil;
import jasmine.framework.database.mybatisplus.dao.BaseI18nEntityDAO;
import jasmine.security.rbac.dto.SecFunctionBaseInfoDTO;
import jasmine.security.rbac.mapper.SecFunctionMapper;
import jasmine.security.rbac.model.SecFunction;

import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */

public class SecFunctionDAO extends BaseI18nEntityDAO<SecFunctionMapper, SecFunction> {
    private SecFunctionMapper baseMapper;

    public SecFunctionDAO(SecFunctionMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 查找指定角色被授予的所有功能
     *
     * @param roleIds
     * @return
     */
    public List<SecFunctionBaseInfoDTO> listAllTenantFunctionBaseInfoDTOsByRoleIdsNoI18n(List<Long> roleIds) {
        if (CollectionUtil.isEmpty(roleIds)) {
            return Collections.emptyList();
        }

        return baseMapper.listAllTenantFunctionBaseInfoDTOsByRoleIdsNoI18n(roleIds);
    }

    /**
     * 查找指定资源被授予给的所有功能
     *
     * @param resourceId
     * @return
     */
    public List<SecFunctionBaseInfoDTO> listFunctionBaseInfoDTOsByIdNoI18n(Long resourceId) {
        return baseMapper.listFunctionBaseInfoDTOsByIdNoI18n(resourceId);
    }

}
