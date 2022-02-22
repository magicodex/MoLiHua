package jasmine.security.rbac.service;

import jasmine.core.util.QCollectionUtil;
import jasmine.security.rbac.dto.SecurityFunctionBaseInfoDTO;
import jasmine.security.rbac.mapper.SecurityFunctionMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */
@Service
public class SecurityFunctionService {
    private SecurityFunctionMapper baseMapper;

    public SecurityFunctionService(SecurityFunctionMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 查找指定角色被授予的所有功能
     *
     * @param roleIds
     * @return
     */
    public List<SecurityFunctionBaseInfoDTO> listFunctionBaseInfoDTOsByRoleIds(List<Long> roleIds) {
        if (QCollectionUtil.isEmpty(roleIds)) {
            return Collections.emptyList();
        }

        return baseMapper.listFunctionBaseInfoDTOsByRoleIds(roleIds);
    }

}
