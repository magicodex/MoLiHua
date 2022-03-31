package jasmine.framework.persistence.mybatisplus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jasmine.core.exception.ApplicationException;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.common.constant.CommonMessages;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author mh.z
 */
public class BaseMapperHelper {

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

        // TODO
        return 0;
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

        // TODO
        return 0;
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

        int rowCount = baseMapper.deleteBatchIds(ids);
        if (rowCount < ids.size()) {
            throw new ApplicationException(CommonMessages.DELETE_ROW_COUNT_MISMATCH);
        }

        return rowCount;
    }

}
