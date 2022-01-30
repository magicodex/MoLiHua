package jasmine.security.rbac.service;

import jasmine.core.util.QCollectionUtil;
import jasmine.security.rbac.mapper.SecurityPermissionMapper;
import jasmine.security.rbac.model.SecurityPermission;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */
@Service
public class SecurityPermissionService {
    private SecurityPermissionMapper baseMapper;

    public SecurityPermissionService(SecurityPermissionMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 查找指定 ID 的权限
     *
     * @param ids
     * @return
     */
    public List<SecurityPermission> listByIds(Collection<Long> ids) {
        if (QCollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }

        return baseMapper.selectBatchIds(ids);
    }

}
