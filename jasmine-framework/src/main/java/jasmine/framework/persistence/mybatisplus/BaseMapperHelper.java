package jasmine.framework.persistence.mybatisplus;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import jasmine.core.exception.ApplicationException;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollUtil;
import jasmine.core.util.batch.BatchCallUtil;
import jasmine.core.util.number.LongValue;
import jasmine.framework.common.constant.CommonMessages;
import jasmine.framework.persistence.constant.PersistenceConstants;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author mh.z
 */
public class BaseMapperHelper {
    private static final Log log = LogFactory.getLog(BaseMapperHelper.class);
    private static final int INSERT_BATCH_SIZE = PersistenceConstants.INSERT_BATCH_SIZE;
    private static final int UPDATE_BATCH_SIZE = PersistenceConstants.UPDATE_BATCH_SIZE;
    private static final int DELETE_BATCH_SIZE = PersistenceConstants.DELETE_BATCH_SIZE;

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
        LongValue rowCount = new LongValue(0);

        SqlHelper.executeBatch(entityClass, log, entities, INSERT_BATCH_SIZE, (sqlSession, entity) -> {
            rowCount.add(sqlSession.insert(sqlStatement, entity));
        });

        return (int) rowCount.get();
    }

    /**
     * 批量更新记录
     *
     * @param baseMapper
     * @param entities
     * @param <T>
     * @return
     */
    public static <T> int updateBatchById(@Nonnull BaseMapper<T> baseMapper,
                                          @Nonnull Collection<T> entities) {
        QCheckUtil.notNull(baseMapper, "baseMapper null");
        QCheckUtil.notNull(entities, "entities null");

        if (entities.isEmpty()) {
            return 0;
        }

        String sqlStatement = SqlHelper.getSqlStatement(baseMapper.getClass(), SqlMethod.UPDATE_BY_ID);
        Class<?> entityClass = QCollUtil.getFirst(entities).getClass();
        LongValue rowCount = new LongValue(0);

        SqlHelper.executeBatch(entityClass, log, entities, UPDATE_BATCH_SIZE, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            rowCount.add(sqlSession.update(sqlStatement, param));
        });

        return (int) rowCount.get();
    }

    /**
     * 更新记录并检查影响的行数
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
        if (rowCount == 0) {
            throw new ApplicationException(CommonMessages.UPDATE_ROW_COUNT_MISMATCH);
        }

        return rowCount;
    }

    /**
     * 更新记录并检查影响的行数
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

        int rowCount = updateBatchById(baseMapper, entities);
        if (rowCount < entities.size()) {
            throw new ApplicationException(CommonMessages.UPDATE_ROW_COUNT_MISMATCH);
        }

        return rowCount;
    }

    /**
     * 删除记录并检查影响的行数
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
        if (rowCount == 0) {
            throw new ApplicationException(CommonMessages.DELETE_ROW_COUNT_MISMATCH);
        }

        return rowCount;
    }

    /**
     * 删除记录并检查影响的行数
     *
     * @param baseMapper
     * @param ids
     * @param <T>
     * @return
     */
    public static <T> int strictDeleteByIds(@Nonnull BaseMapper<T> baseMapper,
                                            @Nonnull Collection<Serializable> ids) {
        QCheckUtil.notNull(baseMapper, "baseMapper null");
        QCheckUtil.notNull(ids, "ids null");

        if (ids.isEmpty()) {
            return 0;
        }

        LongValue rowCount = new LongValue(0);
        BatchCallUtil.call(ids, DELETE_BATCH_SIZE, (part) -> {
            rowCount.add(baseMapper.deleteBatchIds(part));
        });

        if (rowCount.get() < ids.size()) {
            throw new ApplicationException(CommonMessages.DELETE_ROW_COUNT_MISMATCH);
        }

        return (int) rowCount.get();
    }

}
