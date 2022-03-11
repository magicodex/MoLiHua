package jasmine.framework.persistence.mybatisplus.i18n;

import com.baomidou.mybatisplus.core.metadata.IPage;
import jasmine.core.util.QCollectionUtil;
import jasmine.framework.persistence.entity.BaseI18nEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */
public class I18nEntityHelper {
    private static I18nEntityFacade i18nFacade;

    public static void initUtil(I18nEntityFacade i18nEntityFacade) {
        I18nEntityHelper.i18nFacade = i18nEntityFacade;
    }

    /**
     * 插入多语言
     *
     * @param entity
     * @return
     */
    public static <T extends BaseI18nEntity> int insertI18n(T entity) {
        return i18nFacade.insertI18n(Collections.singletonList(entity));
    }

    /**
     * 修改多语言
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T extends BaseI18nEntity> T updateI18nThenFillEntity(T entity) {
        List<T> entityList = i18nFacade.updateI18nThenFillEntity(Collections.singletonList(entity));

        return QCollectionUtil.getFirst(entityList);
    }

    /**
     * 关联多语言
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T extends BaseI18nEntity> T populateI18n(T entity) {
        List<T> entityList = i18nFacade.populateI18n(Collections.singletonList(entity));

        return QCollectionUtil.getFirst(entityList);
    }

    /**
     * 关联多语言
     *
     * @param entities
     * @param <T>
     * @return
     */
    public static <T extends BaseI18nEntity> List<T> populateI18n(Collection<T> entities) {
        return i18nFacade.populateI18n(entities);
    }

    /**
     * 关联多语言
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T extends BaseI18nEntity> IPage<T> populateI18n(IPage<T> page) {
        List<T> recordList = page.getRecords();

        if (QCollectionUtil.isNotEmpty(recordList)) {
            recordList = populateI18n(recordList);
            page.setRecords(recordList);
        }

        return page;
    }

    /**
     * 删除多语言
     *
     * @param id
     * @return
     */
    public static int deleteI18n(Serializable id) {
        return i18nFacade.deleteI18n(Collections.singletonList(id));
    }

    /**
     * 删除多语言
     *
     * @param ids
     * @return
     */
    public static int deleteI18n(Collection<? extends Serializable> ids) {
        return i18nFacade.deleteI18n(ids);
    }

}
