package jasmine.security.rbac.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jasmine.core.util.QCollectionUtil;
import jasmine.security.rbac.mapper.SecurityFunctionPermissionMapper;
import jasmine.security.rbac.model.SecurityFunctionPermission;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */
@Service
public class SecurityFunctionPermissionService {
    private SecurityFunctionPermissionMapper baseMapper;

    public SecurityFunctionPermissionService(SecurityFunctionPermissionMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 查找指定功能的权限关系
     *
     * @param functionIds
     * @return
     */
    public List<SecurityFunctionPermission> listByFunctions(Collection<Long> functionIds) {
        if (QCollectionUtil.isEmpty(functionIds)) {
            return Collections.emptyList();
        }

        LambdaQueryWrapper<SecurityFunctionPermission> wrapper = Wrappers.lambdaQuery();
        wrapper.in(SecurityFunctionPermission::getFunctionId, functionIds);
        List<SecurityFunctionPermission> recordList = baseMapper.selectList(wrapper);

        return recordList;
    }

}
