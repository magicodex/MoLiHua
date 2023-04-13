package jasmine.framework.database.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.database.mybatisplus.entity.BaseI18nEntity;
import jasmine.framework.database.mybatisplus.i18n.I18nEntityUtil;

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
        CheckUtil.notNull(entity, "entity null");

        super.save(entity);
        I18nEntityUtil.insertI18n(entity);
    }

    @Override
    public void saveBatch(Collection<T> entities) {
        CheckUtil.notNull(entities, "entities null");

        super.saveBatch(entities);
        I18nEntityUtil.insertI18n(entities);
    }

    @Override
    public void updateById(T entity) {
        CheckUtil.notNull(entity, "entity null");

        I18nEntityUtil.updateI18nThenFillEntity(entity);
        super.updateById(entity);
    }

    @Override
    public void updateBatchById(Collection<T> entities) {
        CheckUtil.notNull(entities, "entities null");

        I18nEntityUtil.updateI18nThenFillEntities(entities);
        super.updateBatchById(entities);
    }

    @Override
    public boolean deleteById(Serializable id) {
        CheckUtil.notNull(id, "id null");

        I18nEntityUtil.deleteI18n(entityClass, id);
        boolean result = super.deleteById(id);

        return result;
    }

    @Override
    public int deleteByIds(Collection<? extends Serializable> ids) {
        CheckUtil.notNull(ids, "ids null");

        I18nEntityUtil.deleteI18n(entityClass, ids);
        int result = super.deleteByIds(ids);

        return result;
    }

    @Override
    public T getById(Serializable id) {
        CheckUtil.notNull(id, "id null");

        T record = super.getById(id);
        record = I18nEntityUtil.populateI18n(record);

        return record;
    }

    @Override
    public List<T> listByIds(Collection<? extends Serializable> ids) {
        CheckUtil.notNull(ids, "ids null");

        List<T> recordList = super.listByIds(ids);
        recordList = I18nEntityUtil.populateI18n(recordList);

        return recordList;
    }

}
