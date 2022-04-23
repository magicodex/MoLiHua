package jasmine.framework.persistence.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.persistence.entity.BaseI18nEntity;
import jasmine.framework.persistence.mybatisplus.i18n.I18nEntityHelper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author mh.z
 */
public class BaseI18nEntityDAO<M extends BaseMapper<T>, T extends BaseI18nEntity>
        extends BaseEntityDAO<M, T> {
    private Class<T> entityClass;

    public BaseI18nEntityDAO() {
        this.entityClass = (Class<T>) ReflectionKit
                .getSuperClassGenericType(getClass(), BaseI18nEntityDAO.class, 1);
    }

    @Override
    public void save(T entity) {
        QCheckUtil.notNull(entity, "entity null");

        super.save(entity);
        I18nEntityHelper.insertI18n(entity);
    }

    @Override
    public void saveBatch(Collection<T> entities) {
        QCheckUtil.notNull(entities, "entities null");

        super.saveBatch(entities);
        I18nEntityHelper.insertI18n(entities);
    }

    @Override
    public void updateById(T entity) {
        QCheckUtil.notNull(entity, "entity null");

        I18nEntityHelper.updateI18nThenFillEntity(entity);
        super.updateById(entity);
    }

    @Override
    public void updateBatchById(Collection<T> entities) {
        QCheckUtil.notNull(entities, "entities null");

        I18nEntityHelper.updateI18nThenFillEntities(entities);
        super.updateBatchById(entities);
    }

    @Override
    public boolean deleteById(Serializable id) {
        QCheckUtil.notNull(id, "id null");

        I18nEntityHelper.deleteI18n(entityClass, id);
        boolean result = super.deleteById(id);

        return result;
    }

    @Override
    public int deleteByIds(Collection<? extends Serializable> ids) {
        QCheckUtil.notNull(ids, "ids null");

        I18nEntityHelper.deleteI18n(entityClass, ids);
        int result = super.deleteByIds(ids);

        return result;
    }

    @Override
    public T getById(Serializable id) {
        QCheckUtil.notNull(id, "id null");

        T record = super.getById(id);
        record = I18nEntityHelper.populateI18n(record);

        return record;
    }

    @Override
    public List<T> listByIds(Collection<? extends Serializable> ids) {
        QCheckUtil.notNull(ids, "ids null");

        List<T> recordList = super.listByIds(ids);
        recordList = I18nEntityHelper.populateI18n(recordList);

        return recordList;
    }

}
