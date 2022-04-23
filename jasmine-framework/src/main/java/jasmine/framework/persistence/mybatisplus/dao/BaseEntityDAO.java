package jasmine.framework.persistence.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.persistence.entity.BaseEntity;
import jasmine.framework.persistence.mybatisplus.util.BaseMapperHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
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
        QCheckUtil.notNull(entity, "entity null");

        baseMapper.insert(entity);
    }

    @Override
    public void saveBatch(Collection<T> entities) {
        BaseMapperHelper.saveBatch(baseMapper, entities);
    }

    @Override
    public void updateById(T entity) {
        BaseMapperHelper.strictUpdateById(baseMapper, entity);
    }

    @Override
    public void updateBatchById(Collection<T> entities) {
        BaseMapperHelper.strictUpdateBatchById(baseMapper, entities);
    }

    @Override
    public boolean deleteById(Serializable id) {
        QCheckUtil.notNull(id, "id null");

        return SqlHelper.retBool(baseMapper.deleteById(id));
    }

    @Override
    public void strictDeleteById(@Nonnull Serializable id) {
        BaseMapperHelper.strictDeleteById(baseMapper, id);
    }

    @Override
    public int deleteByIds(Collection<? extends Serializable> ids) {
        return BaseMapperHelper.strictDeleteByIds(baseMapper, ids);
    }

    @Override
    public void strictDeleteByIds(@Nonnull Collection<? extends Serializable> ids) {
        BaseMapperHelper.strictDeleteByIds(baseMapper, ids);
    }

    @Override
    public T getById(Serializable id) {
        QCheckUtil.notNull(id, "id null");

        return baseMapper.selectById(id);
    }

    @Override
    public List<T> listByIds(Collection<? extends Serializable> ids) {
        QCheckUtil.notNull(ids, "ids null");

        if (ids.isEmpty()) {
            return Collections.emptyList();
        }

        return baseMapper.selectBatchIds(ids);
    }

}
