package jasmine.framework.persistence.mybatisplus.i18n;

import com.baomidou.mybatisplus.core.metadata.IPage;
import jasmine.core.util.QCollectionUtil;

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
     * @param <T>
     * @return
     */
    public static <T> T insertI18n(T entity) {
        return i18nFacade.insertI18n(entity);
    }

    /**
     * 修改多语言
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> T updateI18n(T entity) {
        return i18nFacade.updateI18n(entity);
    }

    /**
     * 关联多语言
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> T populateI18n(T entity) {
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
    public static <T> List<T> populateI18n(Collection<T> entities) {
        return i18nFacade.populateI18n(entities);
    }

    /**
     * 关联多语言
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T> IPage<T> populateI18n(IPage<T> page) {
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
