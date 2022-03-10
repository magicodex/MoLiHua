package jasmine.security.rbac.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jasmine.security.rbac.dto.SecFunctionBaseInfoDTO;
import jasmine.security.rbac.mapper.SecResourceMapper;
import jasmine.security.rbac.model.SecResource;

import java.util.List;

/**
 * @author mh.z
 */

public class SecResourceDao {
    private SecResourceMapper baseMapper;

    public SecResourceDao(SecResourceMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 查找指定路径的资源
     *
     * @param path
     * @return
     */
    public List<SecResource> listResourcesByPath(String path) {
        LambdaQueryWrapper<SecResource> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SecResource::getResourcePath, path);
        wrapper.orderByAsc(SecResource::getResourcePath);
        wrapper.orderByAsc(SecResource::getAccessMethod);

        return baseMapper.selectList(wrapper);
    }

    /**
     * 查找指定资源被授予给的所有功能
     *
     * @param resourceId
     * @return
     */
    public List<SecFunctionBaseInfoDTO> listFunctionBaseInfoDTOsById(Long resourceId) {
        return baseMapper.listFunctionBaseInfoDTOsById(resourceId);
    }

}
