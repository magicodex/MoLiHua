package jasmine.example.persistence.dao;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jasmine.example.persistence.mapper.DemoUserMapper;
import jasmine.example.persistence.model.DemoUserDO;
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
    public DemoUserDO getUserByName(String userName) {
        LambdaQueryWrapper<DemoUserDO> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(DemoUserDO::getUserName, userName);

        return CollectionUtil.getFirst(baseMapper.selectList(wrapper));
    }

}
