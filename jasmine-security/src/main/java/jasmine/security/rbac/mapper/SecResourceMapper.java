package jasmine.security.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jasmine.security.rbac.dto.SecFunctionBaseInfoDTO;
import jasmine.security.rbac.model.SecResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mh.z
 */
public interface SecResourceMapper extends BaseMapper<SecResource> {

    /**
     * 查找指定资源被授予给的所有功能
     *
     * @param resourceId
     * @return
     */
    List<SecFunctionBaseInfoDTO> listFunctionBaseInfoDTOsById(@Param("resourceId") Long resourceId);
}
