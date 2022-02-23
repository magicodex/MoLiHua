package jasmine.demo.authentication.persistence.dao;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jasmine.demo.authentication.persistence.entity.UserEO;
import jasmine.demo.authentication.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author mh.z
 */
@RequiredArgsConstructor
@Repository
public class UserDao {
    private final UserMapper baseMapper;

    /**
     * 根据用户 ID 查找用户
     *
     * @param userId
     * @return
     */
    public UserEO getUserById(Long userId) {
        return baseMapper.selectById(userId);
    }

    /**
     * 根据用户名查找用户
     *
     * @param userName
     * @return
     */
    public UserEO getUserByName(String userName) {
        LambdaQueryWrapper<UserEO> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UserEO::getUserName, userName);

        return CollectionUtil.getFirst(baseMapper.selectList(wrapper));
    }

}
