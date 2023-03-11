package jasmine.mybatis.i18n;

import jasmine.mybatis.entity.BaseI18nEntity;
import jasmine.mybatis.i18n.support.PopulateFunction;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * @author mh.z
 */
public interface I18nEntityFacade {

    /**
     * 插入多语言记录
     *
     * @param entities
     * @return
     */
    int insertI18n(Collection<? extends BaseI18nEntity> entities);

    /**
     * 更新多语言记录
     *
     * @param entities
     * @return
     */
    int updateI18n(Collection<? extends BaseI18nEntity> entities);

    /**
     * 更新多语言记录以及填充实体多语言数据
     *
     * @param entities
     * @return
     */
    int updateI18nThenFillEntities(Collection<? extends BaseI18nEntity> entities);

    /**
     * 删除多语言记录
     *
     * @param entityType
     * @param ids
     * @return
     */
    int deleteI18n(Class<? extends BaseI18nEntity> entityType, Collection<? extends Serializable> ids);

    /**
     * 填充多语言记录
     *
     * @param entities
     * @param <T>
     * @return
     */
    <T extends BaseI18nEntity> List<T> populateI18n(Collection<T> entities);

    /**
     * 填充多语言记录
     *
     * @param targets
     * @param entityType
     * @param keyMapper
     * @param populateFunction
     * @param <T>
     * @param <E>
     * @return
     */
    <T, E extends BaseI18nEntity> List<T> populateI18n(Collection<T> targets, Class<E> entityType,
                                                       Function<T, Serializable> keyMapper,
                                                       PopulateFunction<T, E> populateFunction);

    /**
     * 填充默认多语言记录
     *
     * @param entities
     * @param <T>
     * @return
     */
    <T extends BaseI18nEntity> List<T> populateDefaultI18n(Collection<T> entities);
}
