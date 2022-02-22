package jasmine.security.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jasmine.security.rbac.dto.SecFunctionBaseInfoDTO;
import jasmine.security.rbac.model.SecFunction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mh.z
 */
public interface SecFunctionMapper extends BaseMapper<SecFunction> {

    /**
     * 查找指定角色被授予的所有功能
     *
     * @param roleIds
     * @return
     */
    List<SecFunctionBaseInfoDTO> listFunctionBaseInfoDTOsByRoleIds(@Param("roleIds") List<Long> roleIds);
}
