package jasmine.security.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jasmine.security.rbac.model.SecRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mh.z
 */
public interface SecRoleMapper extends BaseMapper<SecRole> {

    /**
     * 查找指定用户被授予的所有角色
     *
     * @param userId
     * @return
     */
    List<SecRole> listRolesByUserId(@Param("userId") Long userId);
}
