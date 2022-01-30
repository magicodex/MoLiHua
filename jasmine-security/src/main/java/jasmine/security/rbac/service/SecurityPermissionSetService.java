package jasmine.security.rbac.service;

import jasmine.core.util.QCollectionUtil;
import jasmine.security.rbac.mapper.SecurityPermissionSetMapper;
import jasmine.security.rbac.model.SecurityPermissionSet;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */
@Service
public class SecurityPermissionSetService {
    private SecurityPermissionSetMapper baseMapper;

    public SecurityPermissionSetService(SecurityPermissionSetMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 查找指定 ID 的权限集
     *
     * @param ids
     * @return
     */
    public List<SecurityPermissionSet> listByIds(Collection<Long> ids) {
        if (QCollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }

        return baseMapper.selectBatchIds(ids);
    }

}
