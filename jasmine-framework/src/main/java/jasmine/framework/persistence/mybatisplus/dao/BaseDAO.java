package jasmine.framework.persistence.mybatisplus.dao;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author mh.z
 */
public interface BaseDAO<T> {

    /**
     * 保存记录
     *
     * @param entity
     * @return
     */
    boolean save(@Nonnull T entity);

    /**
     * 保存记录
     *
     * @param entities
     * @return
     */
    boolean saveBatch(@Nonnull Collection<T> entities);

    /**
     * 更新记录
     *
     * @param entity
     * @return
     */
    boolean updateById(@Nonnull T entity);

    /**
     * 更新记录
     *
     * @param entities
     * @return
     */
    boolean updateBatchById(@Nonnull Collection<T> entities);

    /**
     * 删除记录
     *
     * @param id
     * @return
     */
    boolean deleteById(@Nonnull Serializable id);

    /**
     * 删除记录
     *
     * @param ids
     * @return
     */
    boolean deleteByIds(@Nonnull Collection<Serializable> ids);

    /**
     * 查询记录
     *
     * @param id
     * @return
     */
    T getById(@Nonnull Serializable id);

    /**
     * 查询记录
     *
     * @param ids
     * @return
     */
    List<T> listByIds(@Nonnull Collection<Serializable> ids);
}
