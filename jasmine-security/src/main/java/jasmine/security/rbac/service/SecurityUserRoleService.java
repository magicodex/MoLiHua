package jasmine.security.rbac.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jasmine.security.rbac.mapper.SecurityUserRoleMapper;
import jasmine.security.rbac.model.SecurityUserRole;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */
@Service
public class SecurityUserRoleService {
    private SecurityUserRoleMapper baseMapper;

    public SecurityUserRoleService(SecurityUserRoleMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 查找指定用户的角色关系
     *
     * @param userId
     * @return
     */
    public List<SecurityUserRole> listByUser(Long userId) {
        if (userId == null) {
            return Collections.emptyList();
        }

        LambdaQueryWrapper<SecurityUserRole> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SecurityUserRole::getUserId, userId);
        List<SecurityUserRole> recordList = baseMapper.selectList(wrapper);

        return recordList;
    }

}
