package jasmine.framework.persistence.mybatisplus.i18n;

import com.baomidou.mybatisplus.core.metadata.IPage;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollUtil;
import jasmine.core.util.QCollectionUtil;
import jasmine.framework.persistence.entity.BaseI18nEntity;

import javax.annotation.Nonnull;
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
    public static int insertI18n(@Nonnull BaseI18nEntity entity) {
        QCheckUtil.notNull(entity, "entity null");

        return i18nFacade.insertI18n(Collections.singletonList(entity));
    }

    /**
     * 插入多语言
     *
     * @param entities
     * @return
     */
    public static int insertI18n(@Nonnull Collection<? extends BaseI18nEntity> entities) {
        QCheckUtil.notNull(entities, "entities null");

        return i18nFacade.insertI18n(entities);
    }

    /**
     * 修改多语言
     *
     * @param entity
     * @return
     */
    public static int updateI18n(@Nonnull BaseI18nEntity entity) {
        QCheckUtil.notNull(entity, "entity null");

        return i18nFacade.updateI18n(Collections.singletonList(entity));
    }

    /**
     * 修改多语言
     *
     * @param entities
     * @return
     */
    public static int updateI18n(@Nonnull Collection<? extends BaseI18nEntity> entities) {
        QCheckUtil.notNull(entities, "entities null");

        return i18nFacade.updateI18n(entities);
    }

    /**
     * 删除多语言
     *
     * @param id
     * @return
     */
    public static int deleteI18n(@Nonnull Serializable id) {
        QCheckUtil.notNull(id, "id null");

        return i18nFacade.deleteI18n(Collections.singletonList(id));
    }

    /**
     * 删除多语言
     *
     * @param ids
     * @return
     */
    public static int deleteI18n(@Nonnull Collection<? extends Serializable> ids) {
        QCheckUtil.notNull(ids, "ids null");

        return i18nFacade.deleteI18n(ids);
    }

    /**
     * 关联多语言
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T extends BaseI18nEntity> T populateI18n(@Nonnull T entity) {
        QCheckUtil.notNull(entity, "entity null");
        List<T> recordList = i18nFacade.populateI18n(Collections.singletonList(entity), entity.getClass());

        return QCollectionUtil.getFirst(recordList);
    }

    /**
     * 关联多语言
     *
     * @param target
     * @param entityType
     * @param <T>
     * @return
     */
    public static <T> T populateI18n(@Nonnull T target,
                                     @Nonnull Class<? extends BaseI18nEntity> entityType) {
        QCheckUtil.notNull(target, "target null");
        QCheckUtil.notNull(entityType, "entityType null");
        List<T> recordList = i18nFacade.populateI18n(Collections.singletonList(target), entityType);

        return QCollectionUtil.getFirst(recordList);
    }

    /**
     * 关联多语言
     *
     * @param entities
     * @param <T>
     * @return
     */
    public static <T extends BaseI18nEntity> List<T> populateI18n(@Nonnull Collection<T> entities) {
        QCheckUtil.notNull(entities, "entities null");

        if (QCollUtil.isEmpty(entities)) {
            return Collections.emptyList();
        }

        T firstRecord = QCollectionUtil.getFirst(entities);
        List<T> recordList = i18nFacade.populateI18n(entities, firstRecord.getClass());

        return recordList;
    }

    /**
     * 关联多语言
     *
     * @param collection
     * @param entityType
     * @param <T>
     * @return
     */
    public static <T> List<T> populateI18n(@Nonnull Collection<T> collection,
                                           @Nonnull Class<? extends BaseI18nEntity> entityType) {
        QCheckUtil.notNull(collection, "collection null");
        QCheckUtil.notNull(entityType, "entityType null");

        if (QCollUtil.isEmpty(collection)) {
            return Collections.emptyList();
        }

        List<T> recordList = i18nFacade.populateI18n(collection, entityType);
        return recordList;
    }

    /**
     * 关联多语言
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T extends BaseI18nEntity> IPage<T> populateI18n(@Nonnull IPage<T> page) {
        QCheckUtil.notNull(page, "page null");
        List<T> recordList = page.getRecords();

        if (QCollectionUtil.isNotEmpty(recordList)) {
            recordList = populateI18n(recordList);
            page.setRecords(recordList);
        }

        return page;
    }

    /**
     * 关联多语言
     *
     * @param page
     * @param entityType
     * @param <T>
     * @return
     */
    public static <T> IPage<T> populateI18n(@Nonnull IPage<T> page,
                                            @Nonnull Class<? extends BaseI18nEntity> entityType) {
        QCheckUtil.notNull(page, "page null");
        QCheckUtil.notNull(entityType, "entityType null");
        List<T> recordList = page.getRecords();

        if (QCollectionUtil.isNotEmpty(recordList)) {
            recordList = populateI18n(recordList, entityType);
            page.setRecords(recordList);
        }

        return page;
    }

}
