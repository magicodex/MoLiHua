package jasmine.security.rbac.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jasmine.core.util.QCollectionUtil;
import jasmine.security.rbac.mapper.SecurityFunctionPermissionSetMapper;
import jasmine.security.rbac.model.SecurityFunctionPermissionSet;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */
@Service
public class SecurityFunctionPermissionSetService {
    private SecurityFunctionPermissionSetMapper baseMapper;

    public SecurityFunctionPermissionSetService(SecurityFunctionPermissionSetMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 查找指定功能的权限集关系
     *
     * @param functionIds
     * @return
     */
    public List<SecurityFunctionPermissionSet> listByFunctions(Collection<Long> functionIds) {
        if (QCollectionUtil.isEmpty(functionIds)) {
            return Collections.emptyList();
        }

        LambdaQueryWrapper<SecurityFunctionPermissionSet> wrapper = Wrappers.lambdaQuery();
        wrapper.in(SecurityFunctionPermissionSet::getFunctionId, functionIds);
        List<SecurityFunctionPermissionSet> recordList = baseMapper.selectList(wrapper);

        return recordList;
    }

}
