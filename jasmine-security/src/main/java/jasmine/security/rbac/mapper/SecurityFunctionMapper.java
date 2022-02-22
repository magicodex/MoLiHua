package jasmine.security.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jasmine.security.rbac.dto.SecurityFunctionBaseInfoDTO;
import jasmine.security.rbac.model.SecurityFunction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mh.z
 */
public interface SecurityFunctionMapper extends BaseMapper<SecurityFunction> {

    /**
     * 查找指定角色被授予的所有功能
     *
     * @param roleIds
     * @return
     */
    List<SecurityFunctionBaseInfoDTO> listFunctionBaseInfoDTOsByRoleIds(@Param("roleIds") List<Long> roleIds);
}
