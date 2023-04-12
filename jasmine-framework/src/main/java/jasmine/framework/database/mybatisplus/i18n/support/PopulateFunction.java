package jasmine.framework.database.mybatisplus.i18n.support;

import jasmine.framework.database.mybatisplus.entity.BaseI18nEntity;

/**
 * <p>
 * 填充多语言函数。
 * </p>
 *
 * @author mh.z
 */
public interface PopulateFunction<T, E extends BaseI18nEntity> {

    /**
     * 填充多语言
     *
     * @param target
     * @param entity
     */
    void populate(T target, E entity);
}
