package jasmine.mybatis.i18n;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.reactivex.annotations.NonNull;
import jasmine.core.util.CheckUtil;
import jasmine.core.util.CollectionUtil;
import jasmine.mybatis.entity.BaseI18nEntity;
import jasmine.mybatis.i18n.support.PopulateFunction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @author mh.z
 */
public class I18nEntityUtil {
    private static I18nEntityFacade i18nFacade;

    public static void initUtil(I18nEntityFacade i18nEntityFacade) {
        I18nEntityUtil.i18nFacade = i18nEntityFacade;
    }

    /**
     * 插入多语言
     *
     * @param entity
     * @return
     */
    public static int insertI18n(@Nonnull BaseI18nEntity entity) {
        CheckUtil.notNull(entity, "entity null");

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
        CheckUtil.notNull(entities, "entities null");

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
        CheckUtil.notNull(entity, "entity null");

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
        CheckUtil.notNull(entity, "entity null");

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
        CheckUtil.notNull(entities, "entities null");

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
        CheckUtil.notNull(entities, "entities null");

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
        CheckUtil.notNull(entityType, "entityType null");

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
        CheckUtil.notNull(entityType, "entityType null");
        CheckUtil.notNull(ids, "ids null");

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
    public static <T extends BaseI18nEntity> T populateI18n(@Nullable T entity) {
        if (entity == null) {
            return null;
        }

        if (i18nFacade == null) {
            return entity;
        }

        List<T> recordList = i18nFacade.populateI18n(Collections.singletonList(entity));

        return CollectionUtil.getFirst(recordList);
    }

    /**
     * 填充多语言
     *
     * @param target
     * @param entityType
     * @param keyMapper
     * @param populateFunction
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T, E extends BaseI18nEntity> T populateI18n(@Nonnull T target, @Nonnull Class<E> entityType,
                                                               @Nonnull Function<T, Serializable> keyMapper,
                                                               @Nonnull PopulateFunction<T, E> populateFunction) {
        CheckUtil.notNull(target, "target null");
        CheckUtil.notNull(entityType, "entityType null");
        CheckUtil.notNull(keyMapper, "keyMapper null");
        CheckUtil.notNull(populateFunction, "populateFunction null");

        if (i18nFacade == null) {
            return target;
        }

        List<T> recordList = i18nFacade.populateI18n(Collections.singletonList(target),
                entityType, keyMapper, populateFunction);

        return CollectionUtil.getFirst(recordList);
    }

    /**
     * 填充多语言
     *
     * @param entities
     * @param <T>
     * @return
     */
    public static <T extends BaseI18nEntity> List<T> populateI18n(@Nonnull Collection<T> entities) {
        CheckUtil.notNull(entities, "entities null");

        if (i18nFacade == null) {
            return CollectionUtil.toList(entities);
        }

        return i18nFacade.populateI18n(entities);
    }

    /**
     * 填充多语言
     *
     * @param targets
     * @param entityType
     * @param keyMapper
     * @param populateFunction
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T, E extends BaseI18nEntity> List<T> populateI18n(@Nonnull Collection<T> targets,
                                                                     @Nonnull Class<E> entityType,
                                                                     @Nonnull Function<T, Serializable> keyMapper,
                                                                     @Nonnull PopulateFunction<T, E> populateFunction) {
        CheckUtil.notNull(targets, "targets null");
        CheckUtil.notNull(entityType, "entityType null");
        CheckUtil.notNull(keyMapper, "keyMapper null");
        CheckUtil.notNull(populateFunction, "populateFunction null");

        if (i18nFacade == null) {
            return CollectionUtil.toList(targets);
        }

        return i18nFacade.populateI18n(targets, entityType, keyMapper, populateFunction);
    }

    /**
     * 填充多语言
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T extends BaseI18nEntity> IPage<T> populateI18n(@Nonnull IPage<T> page) {
        CheckUtil.notNull(page, "page null");

        if (i18nFacade == null) {
            return page;
        }

        List<T> recordList = page.getRecords();
        if (CollectionUtil.isNotEmpty(recordList)) {
            recordList = i18nFacade.populateI18n(recordList);
            page.setRecords(recordList);
        }

        return page;
    }

    /**
     * 填充多语言
     *
     * @param page
     * @param entityType
     * @param keyMapper
     * @param populateFunction
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T, E extends BaseI18nEntity> IPage<T> populateI18n(@Nonnull IPage<T> page,
                                                                      @Nonnull Class<E> entityType,
                                                                      @Nonnull Function<T, Serializable> keyMapper,
                                                                      @Nonnull PopulateFunction<T, E> populateFunction) {
        CheckUtil.notNull(page, "page null");
        CheckUtil.notNull(entityType, "entityType null");
        CheckUtil.notNull(keyMapper, "keyMapper null");
        CheckUtil.notNull(populateFunction, "populateFunction null");

        if (i18nFacade == null) {
            return page;
        }

        List<T> recordList = page.getRecords();
        if (CollectionUtil.isNotEmpty(recordList)) {
            recordList = i18nFacade.populateI18n(recordList, entityType, keyMapper, populateFunction);
            page.setRecords(recordList);
        }

        return page;
    }

}
