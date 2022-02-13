package jasmine.security.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jasmine.security.rbac.dto.ResourceBaseInfoDTO;
import jasmine.security.rbac.model.SecurityResource;

import java.util.List;

/**
 * @author mh.z
 */
public interface SecurityResourceMapper extends BaseMapper<SecurityResource> {

    /**
     * 查找指定角色被授予的所有资源
     *
     * @param roleIds
     * @return
     */
    List<ResourceBaseInfoDTO> listResourceBaseInfoDTOsByRoleIds(List<Long> roleIds);
}
