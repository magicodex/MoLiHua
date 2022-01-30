package jasmine.security.rbac.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jasmine.core.util.QCollectionUtil;
import jasmine.security.rbac.mapper.SecurityPermissionSetPermissionMapper;
import jasmine.security.rbac.model.SecurityPermissionSetPermission;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */
@Service
public class SecurityPermissionSetPermissionService {
    private SecurityPermissionSetPermissionMapper baseMapper;

    public SecurityPermissionSetPermissionService(SecurityPermissionSetPermissionMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 查找指定权限集的权限关系
     *
     * @param permissionSetIds
     * @return
     */
    public List<SecurityPermissionSetPermission> listByPermissionSets(Collection<Long> permissionSetIds) {
        if (QCollectionUtil.isEmpty(permissionSetIds)) {
            return Collections.emptyList();
        }

        LambdaQueryWrapper<SecurityPermissionSetPermission> wrapper = Wrappers.lambdaQuery();
        wrapper.in(SecurityPermissionSetPermission::getPermissionSetId, permissionSetIds);
        List<SecurityPermissionSetPermission> recordList = baseMapper.selectList(wrapper);

        return recordList;
    }

}
