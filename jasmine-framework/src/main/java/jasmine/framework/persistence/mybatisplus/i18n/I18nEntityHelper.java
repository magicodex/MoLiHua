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
public class I18nEntityHelper {
    private static I18nEntityFacade i18nEntityFacade;

    public static void initUtil(I18nEntityFacade i18nEntityFacade) {
        I18nEntityHelper.i18nEntityFacade = i18nEntityFacade;
    }

    /**
     * 插入多语言
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> T insertI18n(T entity) {
        return i18nEntityFacade.insertI18n(entity);
    }

    /**
     * 修改多语言
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> T updateI18n(T entity) {
        return i18nEntityFacade.updateI18n(entity);
    }

    /**
     * 关联多语言
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> T populateI18n(T entity) {
        return i18nEntityFacade.populateI18n(entity);
    }

    /**
     * 关联多语言
     *
     * @param entities
     * @param <T>
     * @return
     */
    public static <T> List<T> populateI18n(Collection<T> entities) {
        return i18nEntityFacade.populateI18n(entities);
    }

    /**
     * 关联多语言
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T> IPage<T> populateI18n(IPage<T> page) {
        return i18nEntityFacade.populateI18n(page);
    }

    /**
     * 删除多语言
     *
     * @param id
     * @return
     */
    public static int deleteI18nById(Serializable id) {
        return i18nEntityFacade.deleteI18nById(id);
    }

    /**
     * 删除多语言
     *
     * @param entity
     * @return
     */
    public static int deleteI18nByEntity(BaseEntity entity) {
        return i18nEntityFacade.deleteI18nByEntity(entity);
    }

    /**
     * 删除多语言
     *
     * @param ids
     * @return
     */
    public static int deleteI18nByIds(Collection<? extends Serializable> ids) {
        return i18nEntityFacade.deleteI18nByIds(ids);
    }

    /**
     * 删除多语言
     *
     * @param entities
     * @return
     */
    public static int deleteI18nByEntities(Collection<? extends BaseEntity> entities) {
        return i18nEntityFacade.deleteI18nByEntities(entities);
    }

    /**
     * 删除多语言
     *
     * @param queryWrapper
     * @return
     */
    public static int deleteI18nByWrapper(Wrapper queryWrapper) {
        return i18nEntityFacade.deleteI18nByWrapper(queryWrapper);
    }

    /**
     * 删除多语言
     *
     * @param queryMap
     * @return
     */
    public static int deleteI18nByMap(Map<String, Object> queryMap) {
        return i18nEntityFacade.deleteI18nByMap(queryMap);
    }

}
