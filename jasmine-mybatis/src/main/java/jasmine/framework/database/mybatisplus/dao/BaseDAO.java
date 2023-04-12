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
     */
    void save(@Nonnull T entity);

    /**
     * 更新记录，若更新不成功则会抛出异常
     *
     * @param entity
     */
    void updateById(@Nonnull T entity);

    /**
     * 删除记录，可能不存在要删除的 id
     *
     * @param id
     * @return
     */
    boolean deleteById(@Nonnull Serializable id);

    /**
     * 删除记录并检查是否删除成功，若删除不成功则会抛出异常
     *
     * @param id
     */
    void strictDeleteById(@Nonnull Serializable id);

    /**
     * 查询记录，可能不存在要查询的 id
     *
     * @param id
     * @return
     */
    T getById(@Nonnull Serializable id);
}
