package jasmine.framework.persistence.mybatisplus.i18n;

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
     * @param entity
     * @param <T>
     * @return
     */
    <T> T insertI18n(T entity);

    /**
     * 修改多语言
     *
     * @param entity
     * @param <T>
     * @return
     */
    <T> T updateI18n(T entity);

    /**
     * 关联多语言
     *
     * @param entities
     * @param <T>
     * @return
     */
    <T> List<T> populateI18n(Collection<T> entities);

    /**
     * 删除多语言
     *
     * @param ids
     * @return
     */
    int deleteI18n(Collection<? extends Serializable> ids);
}
