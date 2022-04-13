package jasmine.framework.persistence.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.persistence.entity.BaseEntity;
import jasmine.framework.persistence.mybatisplus.BaseMapperHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */
public class BaseTemplateDAO<M extends BaseMapper<T>, T extends BaseEntity>
        implements BaseDAO<T>, BaseBatchDAO<T> {
    @Autowired
    protected M baseMapper;

    @Override
    public boolean save(T entity) {
        return SqlHelper.retBool(baseMapper.insert(entity));
    }

    @Override
    public boolean saveBatch(Collection<T> entities) {
        return SqlHelper.retBool(BaseMapperHelper.saveBatch(baseMapper, entities));
    }

    @Override
    public boolean updateById(T entity) {
        return SqlHelper.retBool(BaseMapperHelper.strictUpdateById(baseMapper, entity));
    }

    @Override
    public boolean updateBatchById(Collection<T> entities) {
        return SqlHelper.retBool(BaseMapperHelper.strictUpdateBatchById(baseMapper, entities));
    }

    @Override
    public boolean deleteById(Serializable id) {
        return SqlHelper.retBool(BaseMapperHelper.strictDeleteById(baseMapper, id));
    }

    @Override
    public boolean deleteByIds(Collection<Serializable> ids) {
        return SqlHelper.retBool(BaseMapperHelper.strictDeleteByIds(baseMapper, ids));
    }

    @Override
    public T getById(Serializable id) {
        QCheckUtil.notNull(id, "id null");

        return baseMapper.selectById(id);
    }

    @Override
    public List<T> listByIds(Collection<Serializable> ids) {
        QCheckUtil.notNull(ids, "ids null");

        if (ids.isEmpty()) {
            return Collections.emptyList();
        }

        return baseMapper.selectBatchIds(ids);
    }

}
