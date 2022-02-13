package jasmine.security.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jasmine.security.rbac.model.SecurityRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mh.z
 */
public interface SecurityRoleMapper extends BaseMapper<SecurityRole> {

    /**
     * 查找指定用户被授予的所有角色
     *
     * @param userId
     * @return
     */
    List<SecurityRole> listRolesByUserId(@Param("userId") Long userId);
}
