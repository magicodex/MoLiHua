package jasmine.demo.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jasmine.demo.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author mh.z
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据 ID 查找用户
     *
     * @param userId
     * @return
     */
    @InterceptorIgnore(tenantLine = "true")
    User getAllTenantUserById(@Param("userId") Long userId);

    /**
     * 根据名称查找用户
     *
     * @param userName
     * @return
     */
    @InterceptorIgnore(tenantLine = "true")
    User getAllTenantUserByName(@Param("userName") String userName);
}
