package jasmine.framework.persistence.mybatisplus.i18n.support;

import jasmine.framework.persistence.entity.BaseI18nEntity;

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
