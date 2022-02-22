package jasmine.security.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jasmine.security.rbac.dto.SecurityFunctionBaseInfoDTO;
import jasmine.security.rbac.model.SecurityResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mh.z
 */
public interface SecurityResourceMapper extends BaseMapper<SecurityResource> {

    /**
     * 查找指定资源被授予给的所有功能
     *
     * @param resourceId
     * @return
     */
    List<SecurityFunctionBaseInfoDTO> listFunctionBaseInfoDTOsById(@Param("resourceId") Long resourceId);
}
