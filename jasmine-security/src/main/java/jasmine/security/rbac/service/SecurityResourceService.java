package jasmine.security.rbac.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jasmine.core.util.QCollectionUtil;
import jasmine.security.rbac.dto.ResourceBaseInfoDTO;
import jasmine.security.rbac.mapper.SecurityResourceMapper;
import jasmine.security.rbac.model.SecurityResource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */
@Service
public class SecurityResourceService {
    private SecurityResourceMapper baseMapper;

    public SecurityResourceService(SecurityResourceMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 查找指定路径的资源
     *
     * @param path
     * @return
     */
    public List<SecurityResource> listResourcesByPath(String path) {
        LambdaQueryWrapper<SecurityResource> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SecurityResource::getResourcePath, path);
        wrapper.orderByAsc(SecurityResource::getResourcePath);
        wrapper.orderByAsc(SecurityResource::getAccessMethod);

        return baseMapper.selectList(wrapper);
    }

    /**
     * 查找指定角色被授予的所有资源
     *
     * @param roleIds
     * @return
     */
    public List<ResourceBaseInfoDTO> listResourceBaseInfoDTOsByRoleIds(List<Long> roleIds) {
        if (QCollectionUtil.isEmpty(roleIds)) {
            return Collections.emptyList();
        }

        return baseMapper.listResourceBaseInfoDTOsByRoleIds(roleIds);
    }

}
