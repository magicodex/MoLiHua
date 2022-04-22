package jasmine.framework.persistence.mybatisplus;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import jasmine.core.exception.ApplicationException;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollUtil;
import jasmine.core.util.batch.BatchCallUtil;
import jasmine.framework.common.constant.CommonMessages;
import jasmine.framework.persistence.constant.PersistenceConstants;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 扩展 BaseMapper 提供的功能。
 * </p>
 *
 * @author mh.z
 */
public class BaseMapperHelper {
    private static final Log log = LogFactory.getLog(BaseMapperHelper.class);

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
        QCheckUtil.notNull(baseMapper, "baseMapper null");
        QCheckUtil.notNull(entities, "entities null");

        if (entities.isEmpty()) {
            return 0;
        }

        String sqlStatement = SqlHelper.getSqlStatement(baseMapper.getClass(), SqlMethod.INSERT_ONE);
        Class<?> entityClass = QCollUtil.getFirst(entities).getClass();

        SqlHelper.executeBatch(entityClass, log, entities, PersistenceConstants.BATCH_INSERT_SIZE,
                (sqlSession, entity) -> {
                    sqlSession.insert(sqlStatement, entity);
                });

        return entities.size();
    }

    /**
     * 更新记录并检查是否更新成功
     *
     * @param baseMapper
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> int strictUpdateById(@Nonnull BaseMapper<T> baseMapper,
                                           @Nonnull T entity) {
        QCheckUtil.notNull(baseMapper, "baseMapper null");
        QCheckUtil.notNull(entity, "entity null");

        int rowCount = baseMapper.updateById(entity);
        if (rowCount != 1) {
            throw new ApplicationException(CommonMessages.UPDATE_ROW_COUNT_MISMATCH);
        }

        return rowCount;
    }

    /**
     * 更新记录并检查是否更新成功
     *
     * @param baseMapper
     * @param entities
     * @param <T>
     * @return
     */
    public static <T> int strictUpdateBatchById(@Nonnull BaseMapper<T> baseMapper,
                                                @Nonnull Collection<T> entities) {
        QCheckUtil.notNull(baseMapper, "baseMapper null");
        QCheckUtil.notNull(entities, "entities null");

        if (entities.isEmpty()) {
            return 0;
        }

        entities.forEach((entity) -> {
            strictUpdateById(baseMapper, entity);
        });

        return entities.size();
    }

    /**
     * 删除记录并检查是否删除成功
     *
     * @param baseMapper
     * @param id
     * @param <T>
     * @return
     */
    public static <T> int strictDeleteById(@Nonnull BaseMapper<T> baseMapper,
                                           @Nonnull Serializable id) {
        QCheckUtil.notNull(baseMapper, "baseMapper null");
        QCheckUtil.notNull(id, "id null");

        int rowCount = baseMapper.deleteById(id);
        if (rowCount != 1) {
            throw new ApplicationException(CommonMessages.DELETE_ROW_COUNT_MISMATCH);
        }

        return rowCount;
    }

    /**
     * 删除记录并检查是否删除成功
     *
     * @param baseMapper
     * @param ids
     * @param <T>
     * @return
     */
    public static <T> int strictDeleteByIds(@Nonnull BaseMapper<T> baseMapper,
                                            @Nonnull Collection<? extends Serializable> ids) {
        QCheckUtil.notNull(baseMapper, "baseMapper null");
        QCheckUtil.notNull(ids, "ids null");

        if (ids.isEmpty()) {
            return 0;
        }

        Set<? extends Serializable> idSet = new HashSet<>(ids);
        // 分批删除记录
        BatchCallUtil.call(idSet, PersistenceConstants.BATCH_DELETE_SIZE, (partialIds) -> {
            int deleteTotal = baseMapper.deleteBatchIds(partialIds);
            if (deleteTotal != partialIds.size()) {
                throw new ApplicationException(CommonMessages.DELETE_ROW_COUNT_MISMATCH);
            }
        });

        return idSet.size();
    }

}
