package jasmine.framework.persistence.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import jasmine.framework.persistence.entity.BaseEntity;
import jasmine.framework.persistence.mybatisplus.util.BaseMapperHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;
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
    public void save(T entity) {
        BaseMapperHelper.save(baseMapper, entity);
    }

    @Override
    public void saveBatch(Collection<T> entities) {
        BaseMapperHelper.saveBatch(baseMapper, entities);
    }

    @Override
    public void updateById(T entity) {
        BaseMapperHelper.updateById(baseMapper, entity, true);
    }

    @Override
    public void updateBatchById(Collection<T> entities) {
        BaseMapperHelper.updateBatchById(baseMapper, entities, true);
    }

    @Override
    public boolean deleteById(Serializable id) {
        return SqlHelper.retBool(BaseMapperHelper.deleteById(baseMapper, id, false));
    }

    @Override
    public void strictDeleteById(@Nonnull Serializable id) {
        BaseMapperHelper.deleteById(baseMapper, id, true);
    }

    @Override
    public int deleteByIds(Collection<? extends Serializable> ids) {
        return BaseMapperHelper.deleteByIds(baseMapper, ids, false);
    }

    @Override
    public void strictDeleteByIds(@Nonnull Collection<? extends Serializable> ids) {
        BaseMapperHelper.deleteByIds(baseMapper, ids, true);
    }

    @Override
    public T getById(Serializable id) {
        return BaseMapperHelper.getById(baseMapper, id, false);
    }

    @Override
    public List<T> listByIds(Collection<? extends Serializable> ids) {
        return BaseMapperHelper.listByIds(baseMapper, ids, false);
    }

}
