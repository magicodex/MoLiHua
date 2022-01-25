package jasmine.demo.authentication.persistence.dao;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jasmine.demo.authentication.persistence.mapper.DemoUserMapper;
import jasmine.demo.authentication.persistence.entity.DemoUserEntity;
import org.springframework.stereotype.Repository;

/**
 * @author mh.z
 */
@Repository
public class DemoUserDao {
    private DemoUserMapper baseMapper;

    public DemoUserDao(DemoUserMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 根据用户名查找用户
     *
     * @param userName
     * @return
     */
    public DemoUserEntity getUserByName(String userName) {
        LambdaQueryWrapper<DemoUserEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(DemoUserEntity::getUserName, userName);

        return CollectionUtil.getFirst(baseMapper.selectList(wrapper));
    }

}
