package jasmine.framework.persistence.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author mh.z
 */
public class BaseDaoSupport<M extends BaseMapper<T>, T> implements BaseDAO<T> {
    @Autowired
    protected M baseMapper;

    @Override
    public boolean save(T entity) {
        return SqlHelper.retBool(baseMapper.insert(entity));
    }

    @Override
    public T getById(Serializable id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean updateById(T entity) {
        return SqlHelper.retBool(baseMapper.updateById(entity));
    }

    @Override
    public boolean deleteById(Serializable id) {
        return SqlHelper.retBool(baseMapper.deleteById(id));
    }

}
