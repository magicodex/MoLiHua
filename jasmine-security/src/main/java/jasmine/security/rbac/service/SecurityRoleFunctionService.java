package jasmine.security.rbac.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jasmine.core.util.QCollectionUtil;
import jasmine.security.rbac.mapper.SecurityRoleFunctionMapper;
import jasmine.security.rbac.model.SecurityRoleFunction;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */
@Service
public class SecurityRoleFunctionService {
    private SecurityRoleFunctionMapper baseMapper;

    public SecurityRoleFunctionService(SecurityRoleFunctionMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 查找指定角色的功能关系
     *
     * @param roleIds
     * @return
     */
    public List<SecurityRoleFunction> listByRoles(Collection<Long> roleIds) {
        if (QCollectionUtil.isEmpty(roleIds)) {
            return Collections.emptyList();
        }

        LambdaQueryWrapper<SecurityRoleFunction> wrapper = Wrappers.lambdaQuery();
        wrapper.in(SecurityRoleFunction::getRoleId, roleIds);
        List<SecurityRoleFunction> recordList = baseMapper.selectList(wrapper);

        return recordList;
    }

}
