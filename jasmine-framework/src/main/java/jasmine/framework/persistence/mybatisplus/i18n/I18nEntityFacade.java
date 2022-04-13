package jasmine.framework.persistence.mybatisplus.i18n;

import jasmine.framework.persistence.entity.BaseI18nEntity;

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
    int insertI18n(Collection<? extends BaseI18nEntity> entities);

    /**
     * 修改多语言
     *
     * @param entities
     * @return
     */
    int updateI18n(Collection<? extends BaseI18nEntity> entities);

    /**
     * 删除多语言
     *
     * @param ids
     * @return
     */
    int deleteI18n(Collection<? extends Serializable> ids);

    /**
     * 关联多语言
     *
     * @param entities
     * @param <T>
     * @return
     */
    <T extends BaseI18nEntity> List<T> populateI18n(Collection<T> entities);
}
