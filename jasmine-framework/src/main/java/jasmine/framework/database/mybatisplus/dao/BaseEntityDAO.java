package jasmine.framework.database.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jasmine.framework.database.mybatisplus.entity.BaseEntity;
import jasmine.framework.database.mybatisplus.util.MapperExtensionUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author mh.z
 */
public class BaseEntityDAO<M extends BaseMapper<T>, T extends BaseEntity>
        implements BaseDAO<T>, BaseBatchDAO<T> {
    @Autowired
    protected M baseMapper;

    protected void setBaseMapper(M baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public int save(T entity) {
        return MapperExtensionUtil.save(baseMapper, entity);
    }

    @Override
    public int saveBatch(Collection<T> entities) {
        return MapperExtensionUtil.saveBatch(baseMapper, entities);
    }

    @Override
    public int updateById(T entity) {
        return MapperExtensionUtil.updateById(baseMapper, entity, false);
    }

    @Override
    public int strictUpdateById(T entity) {
        return MapperExtensionUtil.updateById(baseMapper, entity, true);
    }

    @Override
    public int updateBatchById(Collection<T> entities) {
        return MapperExtensionUtil.updateBatchById(baseMapper, entities, false);
    }

    @Override
    public int strictUpdateBatchById(Collection<T> entities) {
        return MapperExtensionUtil.updateBatchById(baseMapper, entities, true);
    }

    @Override
    public int deleteById(Serializable id) {
        return MapperExtensionUtil.deleteById(baseMapper, id, false);
    }

    @Override
    public int strictDeleteById(Serializable id) {
        return MapperExtensionUtil.deleteById(baseMapper, id, true);
    }

    @Override
    public int deleteByIds(Collection<? extends Serializable> ids) {
        return MapperExtensionUtil.deleteByIds(baseMapper, ids, false);
    }

    @Override
    public int strictDeleteByIds(Collection<? extends Serializable> ids) {
        return MapperExtensionUtil.deleteByIds(baseMapper, ids, true);
    }

    @Override
    public T getById(Serializable id) {
        return MapperExtensionUtil.getById(baseMapper, id, false);
    }

    @Override
    public List<T> listByIds(Collection<? extends Serializable> ids) {
        return MapperExtensionUtil.listByIds(baseMapper, ids, false);
    }

}
