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
public class BaseI18nTemplateDAO<M extends BaseMapper<T>, T extends BaseI18nEntity>
        extends BaseTemplateDAO<M, T> {
    private Class<T> entityClass;

    public BaseI18nTemplateDAO() {
        this.entityClass = (Class<T>) ReflectionKit
                .getSuperClassGenericType(getClass(), BaseI18nTemplateDAO.class, 1);
    }

    @Override
    public boolean save(T entity) {
        QCheckUtil.notNull(entity, "entity null");

        boolean result = super.save(entity);
        I18nEntityHelper.insertI18n(entity);

        return result;
    }

    @Override
    public boolean saveBatch(Collection<T> entities) {
        QCheckUtil.notNull(entities, "entities null");

        boolean result = super.saveBatch(entities);
        I18nEntityHelper.insertI18n(entities);

        return result;
    }

    @Override
    public boolean updateById(T entity) {
        QCheckUtil.notNull(entity, "entity null");

        boolean result = super.updateById(entity);
        I18nEntityHelper.updateI18n(entity);

        return result;
    }

    @Override
    public boolean updateBatchById(Collection<T> entities) {
        QCheckUtil.notNull(entities, "entities null");

        boolean result = super.updateBatchById(entities);
        I18nEntityHelper.updateI18n(entities);

        return result;
    }

    @Override
    public boolean deleteById(Serializable id) {
        QCheckUtil.notNull(id, "id null");

        boolean result = super.deleteById(id);
        I18nEntityHelper.deleteI18n(entityClass, id);

        return result;
    }

    @Override
    public boolean deleteByIds(Collection<Serializable> ids) {
        QCheckUtil.notNull(ids, "ids null");

        boolean result = super.deleteByIds(ids);
        I18nEntityHelper.deleteI18n(entityClass, ids);

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
    public List<T> listByIds(Collection<Serializable> ids) {
        QCheckUtil.notNull(ids, "ids null");

        List<T> recordList = super.listByIds(ids);
        recordList = I18nEntityHelper.populateI18n(recordList);

        return recordList;
    }

}
