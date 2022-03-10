package jasmine.framework.persistence.mybatisplus.i18n;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jasmine.framework.persistence.entity.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
     * @param entity
     * @param <T>
     * @return
     */
    <T> T populateI18n(T entity);

    /**
     * 关联多语言
     *
     * @param entities
     * @param <T>
     * @return
     */
    <T> List<T> populateI18n(Collection<T> entities);

    /**
     * 关联多语言
     *
     * @param page
     * @param <T>
     * @return
     */
    <T> IPage<T> populateI18n(IPage<T> page);

    /**
     * 删除多语言
     *
     * @param id
     * @return
     */
    int deleteI18nById(Serializable id);

    /**
     * 删除多语言
     *
     * @param entity
     * @return
     */
    int deleteI18nByEntity(BaseEntity entity);

    /**
     * 删除多语言
     *
     * @param ids
     * @return
     */
    int deleteI18nByIds(Collection<? extends Serializable> ids);

    /**
     * 删除多语言
     *
     * @param entities
     * @return
     */
    int deleteI18nByEntities(Collection<? extends BaseEntity> entities);

    /**
     * 删除多语言
     *
     * @param queryWrapper
     * @return
     */
    int deleteI18nByWrapper(Wrapper queryWrapper);

    /**
     * 删除多语言
     *
     * @param queryMap
     * @return
     */
    int deleteI18nByMap(Map<String, Object> queryMap);

}
