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
    public int save(T entity) {
        CheckUtil.notNull(entity, "entity null");

        int rowCount = super.save(entity);
        I18nEntityUtil.insertI18n(entity);

        return rowCount;
    }

    @Override
    public int saveBatch(Collection<T> entities) {
        CheckUtil.notNull(entities, "entities null");

        int rowCount = super.saveBatch(entities);
        I18nEntityUtil.insertI18n(entities);

        return rowCount;
    }

    @Override
    public int updateById(T entity) {
        CheckUtil.notNull(entity, "entity null");

        I18nEntityUtil.updateI18nThenFillEntity(entity);
        int rowCount = super.updateById(entity);

        return rowCount;
    }

    @Override
    public int strictUpdateById(T entity) {
        CheckUtil.notNull(entity, "entity null");

        I18nEntityUtil.updateI18nThenFillEntity(entity);
        int rowCount = super.strictUpdateById(entity);

        return rowCount;
    }

    @Override
    public int updateBatchById(Collection<T> entities) {
        CheckUtil.notNull(entities, "entities null");

        I18nEntityUtil.updateI18nThenFillEntities(entities);
        int rowCount = super.updateBatchById(entities);

        return rowCount;
    }

    @Override
    public int strictUpdateBatchById(Collection<T> entities) {
        CheckUtil.notNull(entities, "entities null");

        I18nEntityUtil.updateI18nThenFillEntities(entities);
        int rowCount = super.strictUpdateBatchById(entities);

        return rowCount;
    }

    @Override
    public int deleteById(Serializable id) {
        CheckUtil.notNull(id, "id null");

        I18nEntityUtil.deleteI18n(entityClass, id);
        int rowCount = super.deleteById(id);

        return rowCount;
    }

    @Override
    public int strictDeleteById(Serializable id) {
        CheckUtil.notNull(id, "id null");

        I18nEntityUtil.deleteI18n(entityClass, id);
        int rowCount = super.strictDeleteById(id);

        return rowCount;
    }

    @Override
    public int deleteByIds(Collection<? extends Serializable> ids) {
        CheckUtil.notNull(ids, "ids null");

        I18nEntityUtil.deleteI18n(entityClass, ids);
        int rowCount = super.deleteByIds(ids);

        return rowCount;
    }

    @Override
    public int strictDeleteByIds(Collection<? extends Serializable> ids) {
        CheckUtil.notNull(ids, "ids null");

        I18nEntityUtil.deleteI18n(entityClass, ids);
        int rowCount = super.strictDeleteByIds(ids);

        return rowCount;
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
