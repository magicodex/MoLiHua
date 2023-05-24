package jasmine.framework.database.mybatisplus.dao;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * <p>
 * CRUD接口。
 * </p>
 *
 * @author mh.z
 */
public interface BaseDAO<T> {

    /**
     * 保存记录，若保存不成功则抛出异常
     *
     * @param entity
     * @return
     */
    int save(@Nonnull T entity);

    /**
     * 更新记录，可能更新不成功
     *
     * @param entity
     * @return
     */
    int updateById(@Nonnull T entity);

    /**
     * 更新记录并检查是否更新成功，若更新不成功则会抛出异常
     *
     * @param entity
     * @return
     */
    int strictUpdateById(@Nonnull T entity);

    /**
     * 删除记录，可能不存在要删除的 id
     *
     * @param id
     * @return
     */
    int deleteById(@Nonnull Serializable id);

    /**
     * 删除记录并检查是否删除成功，若删除不成功则会抛出异常
     *
     * @param id
     * @return
     */
    int strictDeleteById(@Nonnull Serializable id);

    /**
     * 查询记录，可能不存在要查询的 id
     *
     * @param id
     * @return
     */
    T getById(@Nonnull Serializable id);
}
