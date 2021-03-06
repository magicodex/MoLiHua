package jasmine.framework.persistence.mybatisplus.i18n;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.reactivex.annotations.NonNull;
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

        if (i18nFacade == null) {
            return 0;
        }

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

        if (i18nFacade == null) {
            return 0;
        }

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

        if (i18nFacade == null) {
            return 0;
        }

        return i18nFacade.updateI18n(Collections.singletonList(entity));
    }

    /**
     * 修改多语言
     *
     * @param entity
     * @return
     */
    public static int updateI18nThenFillEntity(@Nonnull BaseI18nEntity entity) {
        QCheckUtil.notNull(entity, "entity null");

        if (i18nFacade == null) {
            return 0;
        }

        return i18nFacade.updateI18nThenFillEntities(Collections.singletonList(entity));
    }

    /**
     * 修改多语言
     *
     * @param entities
     * @return
     */
    public static int updateI18n(@Nonnull Collection<? extends BaseI18nEntity> entities) {
        QCheckUtil.notNull(entities, "entities null");

        if (i18nFacade == null) {
            return 0;
        }

        return i18nFacade.updateI18n(entities);
    }

    /**
     * 修改多语言
     *
     * @param entities
     * @return
     */
    public static int updateI18nThenFillEntities(@Nonnull Collection<? extends BaseI18nEntity> entities) {
        QCheckUtil.notNull(entities, "entities null");

        if (i18nFacade == null) {
            return 0;
        }

        return i18nFacade.updateI18nThenFillEntities(entities);
    }

    /**
     * 删除多语言
     *
     * @param entityType
     * @param id
     * @return
     */
    public static int deleteI18n(@NonNull Class<? extends BaseI18nEntity> entityType, @Nonnull Serializable id) {
        QCheckUtil.notNull(entityType, "entityType null");

        if (i18nFacade == null) {
            return 0;
        }

        return i18nFacade.deleteI18n(entityType, Collections.singletonList(id));
    }

    /**
     * 删除多语言
     *
     * @param ids
     * @return
     */
    public static int deleteI18n(@NonNull Class<? extends BaseI18nEntity> entityType,
                                 @Nonnull Collection<? extends Serializable> ids) {
        QCheckUtil.notNull(entityType, "entityType null");
        QCheckUtil.notNull(ids, "ids null");

        if (i18nFacade == null) {
            return 0;
        }

        return i18nFacade.deleteI18n(entityType, ids);
    }

    /**
     * 填充多语言
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T extends BaseI18nEntity> T populateI18n(@Nonnull T entity) {
        QCheckUtil.notNull(entity, "entity null");

        if (i18nFacade == null) {
            return entity;
        }

        List<T> recordList = i18nFacade.populateI18n(Collections.singletonList(entity));
        return QCollectionUtil.getFirst(recordList);
    }

    /**
     * 填充多语言
     *
     * @param entities
     * @param <T>
     * @return
     */
    public static <T extends BaseI18nEntity> List<T> populateI18n(@Nonnull Collection<T> entities) {
        QCheckUtil.notNull(entities, "entities null");

        if (i18nFacade == null) {
            return QCollUtil.toList(entities);
        }

        return i18nFacade.populateI18n(entities);
    }

    /**
     * 填充多语言
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T extends BaseI18nEntity> IPage<T> populateI18n(@Nonnull IPage<T> page) {
        QCheckUtil.notNull(page, "page null");

        if (i18nFacade == null) {
            return page;
        }

        List<T> recordList = page.getRecords();
        if (QCollectionUtil.isNotEmpty(recordList)) {
            recordList = i18nFacade.populateI18n(recordList);
            page.setRecords(recordList);
        }

        return page;
    }

}
