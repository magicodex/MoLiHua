package jasmine.framework.persistence.mybatisplus.i18n;

import jasmine.framework.persistence.entity.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author mh.z
 */
public interface I18nEntityFacade {

    /**
     * 插入多语言
     *
     * @param entities
     * @return
     */
    <T extends BaseEntity> int insertI18n(Collection<T> entities);

    /**
     * 修改多语言
     *
     * @param entities
     * @param <T>
     * @return
     */
    <T extends BaseEntity> List<T> updateI18nThenFillEntity(Collection<T> entities);

    /**
     * 关联多语言
     *
     * @param entities
     * @param <T>
     * @return
     */
    <T extends BaseEntity> List<T> populateI18n(Collection<T> entities);

    /**
     * 删除多语言
     *
     * @param ids
     * @return
     */
    int deleteI18n(Collection<? extends Serializable> ids);
}
