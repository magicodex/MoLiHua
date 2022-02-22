package jasmine.security.rbac.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jasmine.security.rbac.dto.SecurityFunctionBaseInfoDTO;
import jasmine.security.rbac.mapper.SecurityResourceMapper;
import jasmine.security.rbac.model.SecurityResource;
import org.springframework.stereotype.Service;

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
     * 查找指定资源被授予给的所有功能
     *
     * @param resourceId
     * @return
     */
    public List<SecurityFunctionBaseInfoDTO> listFunctionBaseInfoDTOsById(Long resourceId) {
        return baseMapper.listFunctionBaseInfoDTOsById(resourceId);
    }

}
