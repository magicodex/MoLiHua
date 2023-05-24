package jasmine.framework.database.mybatisplus.dao;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 批量CRUD接口。
 * </p>
 *
 * @author mh.z
 */
public interface BaseBatchDAO<T> {

    /**
     * 批量保存记录，若保存不成功则抛出异常
     *
     * @param entities
     * @return
     */
    int saveBatch(@Nonnull Collection<T> entities);

    /**
     * 批量更新记录，可能存在更新不成功的记录
     *
     * @param entities
     * @return
     */
    int updateBatchById(@Nonnull Collection<T> entities);

    /**
     * 批量更新记录并检查是否更新成功，若更新不成功则会抛出异常
     *
     * @param entities
     * @return
     */
    int strictUpdateBatchById(@Nonnull Collection<T> entities);

    /**
     * 批量删除记录，可能不存在要删除的 id
     *
     * @param ids
     * @return
     */
    int deleteByIds(@Nonnull Collection<? extends Serializable> ids);

    /**
     * 批量删除记录并检查是否删除成功，若删除不成功则会抛出异常
     *
     * @param ids
     * @return
     */
    int strictDeleteByIds(@Nonnull Collection<? extends Serializable> ids);

    /**
     * 批量查询记录，可能不存在要查询的 id
     *
     * @param ids
     * @return
     */
    List<T> listByIds(@Nonnull Collection<? extends Serializable> ids);
}
