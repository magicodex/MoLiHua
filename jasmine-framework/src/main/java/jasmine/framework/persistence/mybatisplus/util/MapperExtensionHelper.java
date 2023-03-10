package jasmine.framework.persistence.mybatisplus.util;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import jasmine.core.exception.ApplicationException;
import jasmine.core.util.CheckUtil;
import jasmine.core.util.CollectionUtil;
import jasmine.core.util.ObjectUtil;
import jasmine.core.util.batch.BatchCallUtil;
import jasmine.core.util.wrapper.LongValue;
import jasmine.framework.common.constant.CommonMessages;
import jasmine.framework.persistence.constant.PersistenceConstants;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 扩展 BaseMapper 提供的功能。
 * </p>
 *
 * @author mh.z
 */
public class MapperExtensionHelper {
    private static final Log log = LogFactory.getLog(MapperExtensionHelper.class);

    /**
     * 保存记录
     *
     * @param baseMapper
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> int save(@Nonnull BaseMapper<T> baseMapper,
                               @Nonnull T entity) {
        CheckUtil.notNull(baseMapper, "baseMapper null");
        CheckUtil.notNull(entity, "entity null");

        return baseMapper.insert(entity);
    }

    /**
     * 批量保存记录
     *
     * @param baseMapper
     * @param entities
     * @param <T>
     * @return
     */
    public static <T> int saveBatch(@Nonnull BaseMapper<T> baseMapper,
                                    @Nonnull Collection<T> entities) {
        CheckUtil.notNull(baseMapper, "baseMapper null");
        CheckUtil.notNull(entities, "entities null");

        if (entities.isEmpty()) {
            return 0;
        }

        Class<?>[] interfaces = baseMapper.getClass().getInterfaces();
        Class<?> mapperClass = interfaces[0];
        String sqlStatement = SqlHelper.getSqlStatement(mapperClass, SqlMethod.INSERT_ONE);
        Class<?> entityClass = CollectionUtil.getFirst(entities).getClass();

        SqlHelper.executeBatch(entityClass, log, entities, PersistenceConstants.BATCH_INSERT_SIZE,
                (sqlSession, entity) -> {
                    sqlSession.insert(sqlStatement, entity);
                });

        return entities.size();
    }

    /**
     * 更新记录
     *
     * @param baseMapper
     * @param entity
     * @param strict
     * @param <T>
     * @return
     */
    public static <T> int updateById(@Nonnull BaseMapper<T> baseMapper,
                                     @Nonnull T entity, boolean strict) {
        CheckUtil.notNull(baseMapper, "baseMapper null");
        CheckUtil.notNull(entity, "entity null");

        int rowCount = baseMapper.updateById(entity);
        if (strict && rowCount != 1) {
            throw new ApplicationException(CommonMessages.UPDATE_ROW_COUNT_MISMATCH, null);
        }

        return rowCount;
    }

    /**
     * 批量更新记录
     *
     * @param baseMapper
     * @param entities
     * @param strict
     * @param <T>
     * @return
     */
    public static <T> int updateBatchById(@Nonnull BaseMapper<T> baseMapper,
                                          @Nonnull Collection<T> entities, boolean strict) {
        CheckUtil.notNull(baseMapper, "baseMapper null");
        CheckUtil.notNull(entities, "entities null");

        if (entities.isEmpty()) {
            return 0;
        }

        LongValue rowCount = new LongValue(0);
        entities.forEach((entity) -> {
            rowCount.add(updateById(baseMapper, entity, strict));
        });

        return (int) rowCount.get();
    }

    /**
     * 删除记录
     *
     * @param baseMapper
     * @param id
     * @param strict
     * @param <T>
     * @return
     */
    public static <T> int deleteById(@Nonnull BaseMapper<T> baseMapper,
                                     @Nonnull Serializable id, boolean strict) {
        CheckUtil.notNull(baseMapper, "baseMapper null");

        int rowCount = baseMapper.deleteById(id);
        if (strict && rowCount != 1) {
            throw new ApplicationException(CommonMessages.DELETE_ROW_COUNT_MISMATCH, null);
        }

        return rowCount;
    }

    /**
     * 批量删除记录
     *
     * @param baseMapper
     * @param ids
     * @param strict
     * @param <T>
     * @return
     */
    public static <T> int deleteByIds(@Nonnull BaseMapper<T> baseMapper,
                                      @Nonnull Collection<? extends Serializable> ids,
                                      boolean strict) {
        CheckUtil.notNull(baseMapper, "baseMapper null");
        CheckUtil.notNull(ids, "ids null");

        if (ids.isEmpty()) {
            return 0;
        }

        Set<? extends Serializable> idSet = new HashSet<>(ids);
        LongValue rowCount = new LongValue(0);
        // 分批删除记录
        BatchCallUtil.call(idSet, PersistenceConstants.BATCH_DELETE_SIZE, (partialIds) -> {
            int deleteTotal = baseMapper.deleteBatchIds(partialIds);
            if (strict && deleteTotal != partialIds.size()) {
                throw new ApplicationException(CommonMessages.DELETE_ROW_COUNT_MISMATCH, null);
            }

            rowCount.add(deleteTotal);
        });

        return (int) rowCount.get();
    }

    /**
     * 查询记录
     *
     * @param baseMapper
     * @param id
     * @param strict
     * @param <T>
     * @return
     */
    public static <T> T getById(@Nonnull BaseMapper<T> baseMapper,
                                @Nonnull Serializable id, boolean strict) {
        CheckUtil.notNull(baseMapper, "baseMapper null");

        T entity = baseMapper.selectById(id);
        if (strict && entity == null) {
            throw new ApplicationException(CommonMessages.SELECT_ROW_COUNT_MISMATCH, null);
        }

        return entity;
    }

    /**
     * 批量查询记录
     *
     * @param baseMapper
     * @param ids
     * @param strict
     * @param <T>
     * @return
     */
    public static <T> List<T> listByIds(@Nonnull BaseMapper<T> baseMapper,
                                        @Nonnull Collection<? extends Serializable> ids,
                                        boolean strict) {
        CheckUtil.notNull(baseMapper, "baseMapper null");
        CheckUtil.notNull(ids, "ids null");

        if (ids.isEmpty()) {
            return Collections.emptyList();
        }

        Set<? extends Serializable> idSet = new HashSet<>(ids);
        List<T> entityList = new ArrayList<>();

        BatchCallUtil.call(idSet, PersistenceConstants.BATCH_SELECT_SIZE, (partialIds) -> {
            List<T> newList = baseMapper.selectBatchIds(partialIds);
            entityList.addAll(newList);
        });

        if (strict && ObjectUtil.notEqual(idSet.size(), entityList.size())) {
            throw new ApplicationException(CommonMessages.SELECT_ROW_COUNT_MISMATCH, null);
        }

        return entityList;
    }

}
