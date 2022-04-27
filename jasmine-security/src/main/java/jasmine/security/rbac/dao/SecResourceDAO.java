package jasmine.security.rbac.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jasmine.security.rbac.mapper.SecResourceMapper;
import jasmine.security.rbac.model.SecResource;

import java.util.List;

/**
 * @author mh.z
 */

public class SecResourceDAO {
    private SecResourceMapper baseMapper;

    public SecResourceDAO(SecResourceMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 查找指定路径的资源
     *
     * @param path
     * @return
     */
    public List<SecResource> listResourcesByPathNoI18n(String path) {
        LambdaQueryWrapper<SecResource> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SecResource::getResourcePath, path);
        wrapper.orderByAsc(SecResource::getResourcePath);
        wrapper.orderByAsc(SecResource::getAccessMethod);

        return baseMapper.selectList(wrapper);
    }

}
