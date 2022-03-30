package jasmine.framework.persistence.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author mh.z
 */
public class BaseDaoSupport<M extends BaseMapper<T>, T> implements BaseDAO<T> {
    @Autowired
    protected M baseMapper;
}
